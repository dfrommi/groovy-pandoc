package net.frommknecht.pandoc.types.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target


/**
 * Annotation to mark and configure json conversion. 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonValue {
	/**
	 * Array index of element.
	 */
	int index() default 0
	/**
	 * Second level array index of element.
	 */
	int subindex() default 0
	
	/**
	 * Elements map key.
	 */
	String key() default ""
	
	/**
	 * Closure for custom conversion to json format
	 */
	Class toJson() default Object
	
	/**
	 * Closure for custom initialization of object from json format
	 */
	Class fromJson() default Object
}
