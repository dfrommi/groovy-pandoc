package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class PlainSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['* PlainText',
     new Plain(content: [new Str("PlainText")])
    ]
  ]

}
