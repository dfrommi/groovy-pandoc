package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class StrikeoutSpec extends TypeSpecBase {
  @Shared
  def conversions = [
			['The Text is ~~deleted~~.',
			 new Strikeout(text: [new Str("deleted")])]
		]
}
