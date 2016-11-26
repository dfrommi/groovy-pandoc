package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class SuperscriptSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['2^10^ is 1024.',
     new Superscript(text: [new Str("10")])]
  ]
}
