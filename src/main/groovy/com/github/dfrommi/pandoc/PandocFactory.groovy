package com.github.dfrommi.pandoc

import groovy.transform.Memoized
import com.github.dfrommi.pandoc.convert.JsonToMetaConverter
import com.github.dfrommi.pandoc.convert.JsonToPandocTypeConverter
import com.github.dfrommi.pandoc.convert.JsonTypeConverter
import com.github.dfrommi.pandoc.convert.PandocConverter


/**
 * Factory for default objects
 */
class PandocFactory {
	/**
	 * Pandoc filter
	 * @return the filter
	 */
	@Memoized
	PandocFilter getFilter() {
		new PandocFilter(converter: converter)
	}
	
	/**
	 * Format converter
	 * @return the converter
	 */
	@Memoized
	PandocConverter getConverter() {
		new PandocConverter(jsonToMetaConverter: jsonToMetaConverter, jsonToPandocConverter: jsonToPandocTypeConverter)
	}
	
	/**
	 * Converter between json and Metadata object
	 * @return the converter
	 */
	@Memoized
	JsonTypeConverter getJsonToMetaConverter() {
		new JsonToMetaConverter(converter: jsonToPandocTypeConverter)
	}

	/**
	 * Converter between json and Pandoc objects
	 * @return the converter
	 */
	@Memoized
	JsonTypeConverter getJsonToPandocTypeConverter() {
		new JsonToPandocTypeConverter()
	}
}
