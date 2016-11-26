package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class LineBreakSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ["First  \nsecond", new LineBreak()]
  ]
}
