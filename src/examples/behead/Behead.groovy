#!/usr/bin/env groovy -cp GroovyPandoc.jar

import static net.frommknecht.pandoc.Pandoc.*
import net.frommknecht.pandoc.types.*

toJSONFilter(Header) { Header h ->
	if(h.level >= 2) {
		new Para(new Emph(text: h.text))
	}
}