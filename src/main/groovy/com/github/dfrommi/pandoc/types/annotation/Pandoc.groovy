package com.github.dfrommi.pandoc.types.annotation;

import groovy.transform.AnnotationCollector
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Annotation collector for pandoc types.
 */
@Canonical
@ToString(includeNames=true, includePackage = false)
@EqualsAndHashCode()
@AnnotationCollector
@interface Pandoc {

}
