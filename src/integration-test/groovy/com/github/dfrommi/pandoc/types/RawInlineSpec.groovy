package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class RawInlineSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['Some text and then <svg> element',
     new RawInline(format: "html", content: "<svg>")]
  ]
}
