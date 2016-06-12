package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class BlockQuoteSpec extends TypeSpecBase {
  @Shared
  def conversions = [
		['> aBlockQuote',
		 new BlockQuote(quote: [new Para(content: new Str("aBlockQuote"))])]
	]

  def "children are blocks"() {
    when:
		def bq = new BlockQuote(quote: [new Para(content: new Str("aBlockQuote"))])
    then:
    bq.children.containsKey('quote')
		bq.quote in Block[]
	}
}
