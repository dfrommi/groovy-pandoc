package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class MathSpec extends TypeSpecBase {
  @Shared
  def conversions = [
			['Here some inline $math$ stuff',
			 new Math(inline: true, math: 'math')],
			['$$display math$$',
			 new Math(inline: false, math: 'display math')]
		]
}
