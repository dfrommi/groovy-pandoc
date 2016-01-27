package com.github.dfrommi.pandoc

import com.github.dfrommi.pandoc.convert.PandocConverter
import com.github.dfrommi.pandoc.types.PandocType

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
	 * @return The modified node
	 */
	def walk(node, meta, action) {
		def res
		if(node in List) {
			res = node.collect {
				callActionWithOptionalMeta(it, meta, action)
			}.findAll { it != [] }
		} else {
			res = callActionWithOptionalMeta(node, meta, action)
		}

		(res as List).each { currentRes ->
			currentRes.children.each{ key, childValues ->
				currentRes[key] = childValues.collect { child ->
					def walkResult = walk(child, meta, action)
					// ?: not possible, because false has to return walkResult
					walkResult != null ? walkResult : child
				}.flatten()
			}
		}
	
		res
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
}
