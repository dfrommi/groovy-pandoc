package net.frommknecht.pandoc.util

/**
 * Some helper methods to simplify annotation processing.
 */
class AnnotationHelper {
	/**
	 * Finds all fields of given class having given annotation.
	 * @param targetClass The class to search on.
	 * @param annotation The annotation-
	 * @return Listof AnnotatedFieldInfo objects.
	 */
	static findAllFieldsWithAnnotation(Class targetClass, Class annotation) {
		def result = []
		
		getAllClasses(targetClass).each { Class<?> theClass ->
			theClass.declaredFields.each {
				def thisAnnotation = it.getAnnotation(annotation)
				if(thisAnnotation) {
					//TODO: Provide generics type info
					result << new AnnotatedFieldInfo(name: it.name, field: it, annotation: thisAnnotation)
				}
			}
		}
		result
	}
	
	/**
	 * Gets class and all it superclasses up to a given class.
	 * 
	 * @param targetClass The target class
	 * @param upTo Search stops at this class latest.
	 * @return The list of classes
	 */
	static Class<?>[] getAllClasses(Class targetClass, Class<?> upTo = Object) {
		Class currentClass = targetClass
		def classes = [currentClass]
		
		while(currentClass.getSuperclass() && currentClass != upTo) {
			currentClass = currentClass.getSuperclass()
			classes << currentClass
		}
		classes
	}
}
