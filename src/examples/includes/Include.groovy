#!/usr/bin/env groovy

@GrabResolver('https://jitpack.io')
@Grab('com.github.dfrommi:groovy-pandoc')
import static net.frommknecht.pandoc.Pandoc.*
import net.frommknecht.pandoc.types.*

toJSONFilter(CodeBlock) { CodeBlock cb ->
	def include = cb.attr.properties["include"]
	if(include) {
		cb.code = new File(include).text
	}
	cb
}
