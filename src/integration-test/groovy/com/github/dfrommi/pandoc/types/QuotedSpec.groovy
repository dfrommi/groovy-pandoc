package com.github.dfrommi.pandoc.types

import org.junit.Ignore
import spock.lang.Shared

@Ignore("Find way to create Quoted from markdown.")
class QuotedSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ["Hallo", new Quoted(type: Quoted.QuoteType.SingleQuote, text: [new Str("Hallo")])]
  ]
}
