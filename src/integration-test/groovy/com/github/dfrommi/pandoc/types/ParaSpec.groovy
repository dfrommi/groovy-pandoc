package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class ParaSpec extends TypeSpecBase {
  @Shared
  def conversions = [
			['Text',
			 new Para(content: new Str("Text"))]
		]
}
