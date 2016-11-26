package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class EmphSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ["Some *emph* text.",
     new Emph(text: [new Str("emph")])]
  ]
}
