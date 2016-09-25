package com.github.dfrommi.pandoc

import com.github.dfrommi.pandoc.convert.PandocConverter
import com.github.dfrommi.pandoc.types.CodeBlock
import com.github.dfrommi.pandoc.types.Header
import com.github.dfrommi.pandoc.types.HorizontalRule
import com.github.dfrommi.pandoc.types.Para
import com.github.dfrommi.pandoc.types.Space
import com.github.dfrommi.pandoc.types.Str
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class PandocFilterSpec extends Specification {
	def oldSystemIn = null
	def oldSystemOut = null
	
	@Shared PandocFilter filter = Pandoc.factory.filter
	@Shared PandocConverter converter = Pandoc.factory.converter
	
	def setup() {
		oldSystemIn = System.in
		oldSystemOut = System.out
	}
	
	def cleanup() {
		System.in = oldSystemIn
		System.out = oldSystemOut
	}

  @Unroll //@IgnoreRest
  def "toJSONFilter transformation #action elements"() {
    given:
    String input = converter.mdToJsonText(mdInput)
    String expected = converter.mdToJsonText(mdExpectedResult)
    def expectedJson = converter.jsonTextToJson(expected)

    def outContent = hijackStreams(input)

    when:
    filter.toJSONFilter(transformation)

    then:
    String result = outContent.toString()
    def actualJson = converter.jsonTextToJson(result)

    actualJson == expectedJson

    where:
    [action, mdInput, mdExpectedResult, transformation] << [
      ["replaces", "Hello world", "HELLO WORLD", { if(it in Str) new Str(it.text.toUpperCase()) }],
      ["removes text", "Hello world", "Hello", { if((it in Str && it.text == "world") || it in Space) []} ],
      ["adds", "Hello world", "Good morning world", { if (it in Str && it.text == "Hello") [new Str("Good"), new Space(), new Str("morning")] }],
      ["replaces multiple", "# Some header\n\nSome text", "Header1\n\nHeader2\n\nSome text", { if(it in Header) [new Para(new Str("Header1")), new Para(new Str("Header2"))] }],
      ["removes","# Header\n\nSome text.\n\n-----\n\nSome more text", "# Header\n\nSome text.\n\nSome more text", {if(it in HorizontalRule) [] } ]
    ]
  }

  def "toJSONFilter processes elements in DFS sequence"() {
    given:
    String input = converter.mdToJsonText(mdInput)
    def classSequence = []
    hijackStreams(input)

    when:
    filter.toJSONFilter({
      classSequence << it.getClass()
      null
    })

    then:
    classSequence == expectedSequence

    where:
    mdInput | expectedSequence
    "Hello world" | [Para, Str, Space, Str]
    "# Header\n\nSome paragraph\n\n```\nSome code\n```\n" | [Header, Str, Para, Str, Space, Str, CodeBlock]
  }

  @Unroll
  def "toJSONFilter transformation of test document #i transforms successfully"() {
    given:
    String mdInput = this.getClass().getResourceAsStream("/test-${i}.md").text
    String input = converter.mdToJsonText(mdInput)
    def expectedJson = converter.jsonTextToJson(input)
    def outContent = hijackStreams(input)

    when:
    filter.toJSONFilter { elem ->
      elem
    }

    then:
    String result = outContent.toString()
    def actualJson = converter.jsonTextToJson(result)

    actualJson == expectedJson

    where:
    i << (1..13)
	}

  static private OutputStream hijackStreams(String inputText) {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream()
		ByteArrayInputStream inContent = new ByteArrayInputStream(inputText.getBytes())

		System.out = new PrintStream(outContent)
		System.in = inContent
		
		outContent
	}
}
