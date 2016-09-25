package com.github.dfrommi.pandoc

import com.github.dfrommi.pandoc.convert.PandocConverter
import com.github.dfrommi.pandoc.types.PandocType
import com.github.dfrommi.pandoc.util.Walkable

class PandocFilter {
	/**
	 * The type converter
	 */
	PandocConverter converter
	
	/**
	 * Process all elements of document having the given type.
	 * 
	 * The document is read from STDIN and printing the result to STDOUT.
	 *
	 * Closure action has one or two parameters. The first one is the current Pandoc element.
	 * The optional second parameter is Meta object.
	 * 
	 * If closure returns null, then document doesn't change.
	 * Closure return value of empty list removed current element and all children.
	 * If an array of Pandoc types is returned, then current element is replaces by those elements.
	 * 
	 * @param conditionClass Action closure of only executed for elements of this type.
	 * @param action Closure to perform action on object.  
	 */
	def toJSONFilter(Class<? extends PandocType> conditionClass, Closure action) {
		toJSONFilter({it in conditionClass}, action)
	}
	
	/**
	 * Process all elements of document meeting the given condition.
	 * 
	 * The document is read from STDIN and printing the result to STDOUT.
	 *
	 * The condition closure expects Pandoc type element as one and only parameter and returns
	 * boolean value. If the returned value is true, then Closure action is called on this
	 * element.
	 * 
	 * Closure action has one or two parameters. The first one is the current Pandoc element.
	 * The optional second parameter is Meta object.
	 * 
	 * If closure returns null, then document doesn't change.
	 * Closure return value of empty list removed current element and all children.
	 * If an array of Pandoc types is returned, then current element is replaces by those elements.
	 * 
	 * @param condition The condition closure (PandocType -> Boolean)
	 * @param action Closure to perform action on object.  
	 */
	def toJSONFilter(Closure<Boolean> condition, Closure action) {
		if(hasMetaParam(action)) {
			toJSONFilter { node, meta ->
				if(condition(node)) {
					action(node, meta)
				}
			}
		} else {
			toJSONFilter { node ->
				if(condition(node)) {
					action(node)
				}
			}
		}
	}
	
	/**
	 * Process all elements of document meeting the given condition.
	 *
	 * The document is read from STDIN and printing the result to STDOUT.
	 *
	 * Closure action has one or two parameters. The first one is the current Pandoc element.
	 * The optional second parameter is Meta object.
	 * 
	 * If closure returns null, then document doesn't change.
	 * Closure return value of empty list removed current element and all children.
	 * If an array of Pandoc types is returned, then current element is replaces by those elements.
	 * 
	 * @param action Closure to perform action on object.  
	 */
	def toJSONFilter(Closure action) {
		def (meta, elements) = converter.jsonTextToDocument(System.in.text)
		def res = walk(elements, meta, action)
		def jsonRes = converter.documentToJsonText([meta, res])
		print jsonRes
	}
	
	/**
	 * Method to walk through document, processing all children.
	 * 
	 * @param node current mode
	 * @param meta Metadata object
	 * @param action The action closure to perform on node objects
	 * @return The modified node, never `null`
	 */
	def walk(node, meta, action) {
    // If list came in, list is also returned
		if(isCollectionOrArray(node)) {
      def nodeList = node as List
      def transformedNodeList = nodeList.collectMany { // a single node can be replaced by multiple, therefore collectMany
        def currentResult = walk(it, meta, action)
        // Automatically flatten if one node has been replaced by multiple nodes
        (!isCollectionOrArray(it) && isCollectionOrArray(currentResult)) ? currentResult as List : [currentResult]
      }
      return transformedNodeList
    }

    // Transformation of a single node

    def transformedResult = callActionWithOptionalMeta(node, meta, action)

    // `null` means no modification.
    // Result [] (=removing element) is handled in the caller (see collectMany)
    if (transformedResult == null) {
      transformedResult = node
    }

    // Transform all children

    // transformation can be one node or a node list, but never a deeply nested list
    (transformedResult as List).each { Walkable transformedNode ->
      transformedNode.children.each { propertyName, childValues ->
        // key is the property name, value is the child or list of children
        def childResult = walk(childValues, meta, action)
        transformedNode."$propertyName" = childResult
      }
    }

    transformedResult
	}

	private callActionWithOptionalMeta(node, meta, action) {
		def actionResult = hasMetaParam(action) ? action(node, meta) : action(node)
		actionResult != null ? actionResult : node
	}
	
	/**
	 * Check if closure expects Meta parameter
	 * @param action The closure
	 * @return True of Meta object is expected, false otherwise.
	 */
	private hasMetaParam(Closure action) {
		action.maximumNumberOfParameters > 1
	}

  boolean isCollectionOrArray(object) {
    [Collection, Object[]].any { it.isAssignableFrom(object.getClass()) }
  }
}
