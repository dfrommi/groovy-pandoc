package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class SubscriptSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['H~2~O is water.',
     new Subscript(text: [new Str("2")])]
  ]
}
