#!/usr/bin/env groovy

import com.github.dfrommi.pandoc.Pandoc
import com.github.dfrommi.pandoc.convert.PandocConverter
import com.github.dfrommi.pandoc.convert.JsonToPandocTypeConverter
import com.github.dfrommi.pandoc.types.*
import com.att.ecomp.dcae.doc.MarkdownUtils

//
// This filter allows you to include Groovy templates in your markdown:
//     ```include-gtp
//     mytemplate.gt
//     ```
// The output of mytemplate.gt will be, in turn, fed through the pandoc
// AST, which means that templates can include other filters.
//
// To use it, make sure the gt-preprocess.groovy script is in your path and
// that it's executable
//
// 

Pandoc.toJSONFilter(CodeBlock) { CodeBlock cb ->
	if ("include-gtp" in cb.attr.classes) {
		def replacement = ""

		cb.code.eachLine { l ->
			l = l.trim()
			// Preprocess the file first into a temp file
			File.createTempFile("temp", ".tmp").with {
				// Don't need it when we're done
				it.deleteOnExit()

				System.err << "Processing groovy template ${l}\n"

				Process proc = "gt-preprocess.groovy ${l}".execute()
				def sout = new StringBuffer()
				def serr = new StringBuffer()

				proc.consumeProcessOutput(sout, serr)
				proc.waitFor()


				if(serr.size() > 0) {
					System.err << "WARNING: gtp produced stderr ${serr.toString()}\n"
				}

				it.write sout.toString()

				if (proc.exitValue() != 0) {
					System.err << "WARNING: gtp ${l} exited with ${proc.exitValue()}\n"
				}

				replacement = replacement + it.text
			}
		}

		def PandocConverter converter = Pandoc.factory.getConverter()
		def rawJson = converter.jsonTextToJson(converter.mdToJsonText(replacement))
		def doc = converter.jsonToDocument(rawJson)

		doc[1]
	}
}
