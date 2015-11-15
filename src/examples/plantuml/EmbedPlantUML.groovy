#!/usr/bin/env groovy

@GrabResolver('https://jitpack.io')
@Grab('com.github.dfrommi:groovy-pandoc')
import net.frommknecht.pandoc.types.*;
import static net.frommknecht.pandoc.Pandoc.*

toJSONFilter({it in CodeBlock && "plantuml" in it.attr.classes}) { 
	def plantuml = "plantuml -pipe -tsvg".execute()
	plantuml.withWriter { w ->
		w << it.code
	}
	def svgXml = plantuml.text
	new RawBlock(format: "html", content: svgXml.substring(svgXml.indexOf("?>") + 2))
}
