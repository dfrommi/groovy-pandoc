package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class SmallCapsSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['Here are <span style="font-variant:small-caps;">smallcaps</span>',
     new SmallCaps(text: [new Str("smallcaps")])]
  ]
}
