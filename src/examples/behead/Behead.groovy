#!/usr/bin/env groovy

@GrabResolver('https://jitpack.io')
@Grab('com.github.dfrommi:groovy-pandoc')
import static com.github.dfrommi.pandoc.Pandoc.*
import com.github.dfrommi.pandoc.types.*

toJSONFilter(Header) { Header h ->
	if(h.level >= 2) {
		new Para(new Emph(text: h.text))
	}
}