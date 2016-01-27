package com.github.dfrommi.pandoc.util

import java.lang.annotation.Annotation
import java.lang.reflect.Field


/**
 * Info about annotated field
 * @param <T>
 */
class AnnotatedFieldInfo<T extends Annotation> {
	/**
	 * Field name
	 */
	String name
	/**
	 * Full field info 
	 */
	Field field
	/**
	 * The annotation
	 */
	T annotation
	
	/**
	 * Gets the field value from given object.
	 * @param object The object
	 * @return The field value
	 */
	def getFieldValue(object) {
		object[name]
	}
}
