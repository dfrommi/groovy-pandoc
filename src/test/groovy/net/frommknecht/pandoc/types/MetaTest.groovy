package net.frommknecht.pandoc.types;

import static org.junit.Assert.*
import net.frommknecht.pandoc.Pandoc
import net.frommknecht.pandoc.convert.JsonToMetaConverter
import net.frommknecht.pandoc.convert.PandocConverter;

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters


@RunWith(Parameterized)
class MetaTest {
	@Parameter
	public md

	@Parameters
	static getParams() {[
['''---
title: The Title
---'''] as Object[],
['''---
test: true
---'''] as Object[],
['''---
number: 12
---'''] as Object[],
['''---
list: 
 - a: 1
   b: 2
---'''] as Object[],
['''---
abstract: |
  This is the abstract.

  It consists of two paragraphs.
---'''] as Object[],
['''---
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
---'''] as Object[]
	]}

	
	@Test
	public void testMetadataToJson() {
		PandocConverter converter = Pandoc.factory.converter
		JsonToMetaConverter metaConverter = Pandoc.factory.jsonToMetaConverter
		
		def document = converter.mdToJson(md)
		
		def meta = metaConverter.fromJson(document[0])
		def result = metaConverter.toJson(meta)
		
		assertEquals(document[0], result)
	}
}
