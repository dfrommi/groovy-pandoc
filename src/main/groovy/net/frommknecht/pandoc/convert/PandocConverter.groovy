package net.frommknecht.pandoc.convert

import groovy.json.JsonOutput
import groovy.json.JsonSlurper


/**
 * Converter between different markdown representations like pure markdown text,
 * json structure and internal objects.
 */
class PandocConverter {
	String pandocExe = System.getProperty("pandoc") ?: "pandoc"
	
	/**
	 * Converter between json and Pandoc types.
	 */
	JsonToPandocTypeConverter jsonToPandocConverter
	
	/**
	 * Converter between json and Metadata. 
	 */
	JsonToMetaConverter jsonToMetaConverter
	
	/**
	 * Markdown text to Json text.
	 * 
	 * @param md the markdown text
	 * @return the json text
	 */
	String mdToJsonText(final String md) {
		convertWithPandoc("markdown", "json", md)
	}
	
	/**
	 * Json text to markdown text.
	 * 
	 * @param jsonText the Json text
	 * @return the markdown text
	 */
	String jsonTextToMd(final String jsonText) {
		convertWithPandoc("json", "markdown", jsonText)
	}

	/**
	 * Json text to json structure.
	 * 
	 * @param jsonText the json text
	 * @return the json structure
	 */
	def jsonTextToJson(final String jsonText) {
		new JsonSlurper().parseText(jsonText)
	}
	
	/**
	 * Json structure to json text.
	 * 
	 * @param json the json structure 
	 * @return the json text
	 */
	String jsonToJsonText(json) {
		JsonOutput.toJson(json)
	}

	/**
	 * Json structure to document ([Meta, [Pandoc elements]])
	 * 
	 * @param json the json structure
	 * @return the document
	 */
	def jsonToDocument(json) {
		[jsonToMetaConverter.fromJson(json[0]),
		 json[1].collect {jsonToElement(it)}]
	}
	
	/**
	 * Document ([Meta, [Pandoc elements]]) to json structure
	 * 
	 * @param obj the document
	 * @return the json structure
	 */
	def documentToJson(obj) {
		[jsonToMetaConverter.toJson(obj[0]), obj[1].collect{elementToJson(it)}]
	}

	/**
	 * Json to pandoc element
	 * @param json the json structure
	 * @return the pandoc element
	 */
	def jsonToElement(json) {
		jsonToPandocConverter.fromJson(json)
	}
	
	/**
	 * Pandoc element to json structure
	 * @param element the pandoc element
	 * @return the json structure
	 */
	def elementToJson(element) {
		jsonToPandocConverter.toJson(element)
	}
	
	/**
	 * Convert between types using Pandoc tool.
	 * 
	 * @param inputFormat the input format
	 * @param outputFormat the output format
	 * @param input the input text
	 * @return the output text
	 */
	String convertWithPandoc(final String inputFormat, final String outputFormat, final String input) {
		def pandoc = "$pandocExe -f $inputFormat -t $outputFormat".execute()
		pandoc.withWriter { w ->
			w << input
		}
		pandoc.text
	}

	/**
	 * Adds conversion methods not available natively, e.g. mdToDocument().
	 *  
	 * @param name conversion method name
	 * @param args method arguments
	 * @return the method return value
	 */
	def methodMissing(String name, args) {
		String[] nameParts = name.split("To") as String[]
		if(nameParts.size() == 2) {
			def (from, to) = nameParts
			MetaMethod method = this.metaClass.methods.find { it.name.startsWith(from + "To") }
		
			if(method) {
				String newTo = method.name.split("To")[1]
				def newMethodName = newTo[0].toLowerCase() + newTo.substring(1) + "To" + to
				def result = method.invoke(this, args)
				return this."$newMethodName"(result)
			}
		}
		
		throw new MissingMethodException(name, getClass(), args)
	}
}
