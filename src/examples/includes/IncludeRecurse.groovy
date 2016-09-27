#!/usr/bin/env groovy

import com.github.dfrommi.pandoc.Pandoc
import com.github.dfrommi.pandoc.convert.PandocConverter
import com.github.dfrommi.pandoc.convert.JsonToPandocTypeConverter
import com.github.dfrommi.pandoc.types.*


//
// This filter allows you to include other markdown files in
// your markdown. It supports 3 levels of inclusion, which means
// your includes can include markdown.
//
// To get more levels of inclusion, simply chain include filters
// on the command line via pandoc:
//
// E.g.
//    pandoc --filter IncludeRecurse.groovy --filter IncludeRecurse.groovy
//
// You can include as many as you need.
//

includeClosure = {
	if (it in CodeBlock) {
		def cb = it as CodeBlock
		if ("include" in cb.attr.classes) {
			def replacement = ""
			cb.code.eachLine { l ->
				l = l.trim()	
				def f = new File(l)
				replacement = replacement + f.text + "\n"
			}
			
			def PandocConverter converter = Pandoc.factory.getConverter()
			def rawJson = converter.jsonTextToJson(converter.mdToJsonText(replacement))
			def doc = converter.jsonToDocument(rawJson)
      
		  return doc[1]
		}
	}
	return it
}

// Triple include
def pandoc = new Pandoc()
def (meta, elements) = pandoc.factory.getConverter().jsonTextToDocument(System.in.text)
def res = pandoc.factory.getFilter().walk(elements, meta, includeClosure)
def jsonRes = pandoc.factory.getConverter().documentToJsonText([meta, res])

(meta, elements) = pandoc.factory.getConverter().jsonTextToDocument(jsonRes)
res = pandoc.factory.getFilter().walk(elements, meta, includeClosure)
jsonRes = pandoc.factory.getConverter().documentToJsonText([meta, res])

(meta, elements) = pandoc.factory.getConverter().jsonTextToDocument(jsonRes)
res = pandoc.factory.getFilter().walk(elements, meta, includeClosure)
jsonRes = pandoc.factory.getConverter().documentToJsonText([meta, res])

print jsonRes
