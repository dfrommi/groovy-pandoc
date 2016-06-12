package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class CodeSpec extends TypeSpecBase {
  @Shared
  def conversions = [
		['This is `Some code`{#mycode .haskell .numberLines startFrom="100" endAt="200"}',
		 new Code(code: "Some code", attr: new Attributes(identifier: "mycode", classes: ["haskell", "numberLines"],
			properties: [startFrom: "100", endAt: "200"]))]
	]
}
