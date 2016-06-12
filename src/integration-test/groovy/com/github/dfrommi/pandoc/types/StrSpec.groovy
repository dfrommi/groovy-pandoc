package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class StrSpec extends TypeSpecBase {
  @Shared
  def conversions = [
			['TheText',
			 new Str("TheText")]
		]
}
