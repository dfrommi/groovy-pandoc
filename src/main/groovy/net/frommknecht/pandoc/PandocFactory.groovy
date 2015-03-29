package net.frommknecht.pandoc

import groovy.transform.Memoized
import net.frommknecht.pandoc.convert.JsonToMetaConverter
import net.frommknecht.pandoc.convert.JsonToPandocTypeConverter
import net.frommknecht.pandoc.convert.JsonTypeConverter
import net.frommknecht.pandoc.convert.PandocConverter


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
