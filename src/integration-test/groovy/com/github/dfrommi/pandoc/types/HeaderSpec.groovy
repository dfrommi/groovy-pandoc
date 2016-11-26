package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class HeaderSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ["## Headline",
     new Header(level: 2, attr: new Attributes(identifier: "headline"), text: [new Str("Headline")])
    ]
  ]
}
