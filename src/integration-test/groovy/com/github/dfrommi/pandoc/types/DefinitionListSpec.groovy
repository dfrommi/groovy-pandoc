package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class DefinitionListSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ['''
head
  ~ value
''',
     new DefinitionList(items: [
       new DefinitionItem(term: [new Str("head")], definitions: [
         new Plain(content: [new Str("value")])
       ])
     ])
    ]
  ]
}
