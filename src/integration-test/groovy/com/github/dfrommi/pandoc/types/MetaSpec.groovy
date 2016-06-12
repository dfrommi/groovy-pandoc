package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.Pandoc
import com.github.dfrommi.pandoc.convert.JsonToMetaConverter
import com.github.dfrommi.pandoc.convert.PandocConverter
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class MetaSpec extends Specification {
  @Shared
  def parameters = [
'''---
title: The Title
---''',
'''---
test: true
---''',
'''---
number: 12
---''',
'''---
list: 
 - a: 1
   b: 2
---''',
'''---
abstract: |
  This is the abstract.

  It consists of two paragraphs.
---''',
'''---
references:
- id: fenner2012a
  title: One-click science marketing
  author:
  - family: Fenner
    given: Martin
  container-title: Nature Materials
  volume: 11
  URL: 'http://dx.doi.org/10.1038/nmat3283'
  DOI: 10.1038/nmat3283
  issue: 4
  publisher: Nature Publishing Group
  page: 261-263
  type: article-journal
  issued:
    year: 2012
    month: 3
---'''
	]

  @Unroll
  def "meta data #md is converted to JSON correctly"() {
    given:
		PandocConverter converter = Pandoc.factory.converter
		JsonToMetaConverter metaConverter = Pandoc.factory.jsonToMetaConverter
		def document = converter.mdToJson(md)
    def meta = metaConverter.fromJson(document[0])

    when:
		def result = metaConverter.toJson(meta)

    then:
		document[0] == result

    where:
    md << parameters
	}
}
