package net.frommknecht.pandoc.types.annotation;

import groovy.transform.AnnotationCollector
import groovy.transform.Canonical
import groovy.transform.ToString

/**
 * Annotation collector for pandoc types.
 */
@Canonical 
@ToString(includeNames=true)
@AnnotationCollector
@interface Pandoc {

}
