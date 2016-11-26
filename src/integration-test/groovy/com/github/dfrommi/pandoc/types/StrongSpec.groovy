package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class StrongSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['The Text is **strong**.',
     new Strong(text: [new Str("strong")])],
    ['The Text is __strong__.',
     new Strong(text: [new Str("strong")])]
  ]
}
