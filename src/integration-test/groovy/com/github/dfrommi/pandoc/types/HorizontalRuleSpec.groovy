package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class HorizontalRuleSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['* * * * *', new HorizontalRule()],
    ['--------', new HorizontalRule()],
  ]
}
