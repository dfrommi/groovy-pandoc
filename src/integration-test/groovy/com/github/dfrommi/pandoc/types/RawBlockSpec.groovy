package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class RawBlockSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['''
Some text 
			
<svg>text</svg>

''',
     new RawBlock(format: "html", content: "<svg>")]
  ]
}
