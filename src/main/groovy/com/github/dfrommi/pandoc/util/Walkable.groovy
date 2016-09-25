package com.github.dfrommi.pandoc.util

import com.github.dfrommi.pandoc.types.annotation.Child

/**
 * Walkable objects. 
 */
trait Walkable {
	/**
	 * Map of child objects
	 * @return Map with property name as key and child value list as value
	 */
	Map<String, ?> getChildren() {
		def children = [:]
		AnnotationHelper.findAllFieldsWithAnnotation(this.class, Child).each { AnnotatedFieldInfo<Child> afi ->
			children << [(afi.name): afi.getFieldValue(this)]
		}
		children
	}
}
