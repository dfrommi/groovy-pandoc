package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class SpaceSpec extends TypeSpecBase {
  @Shared
  def conversions = [
			['Text with spaces', new Space()]
		]
}
