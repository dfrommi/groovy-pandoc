package com.github.dfrommi.pandoc

import com.github.dfrommi.pandoc.convert.PandocConverter
import com.github.dfrommi.pandoc.types.CodeBlock
import com.github.dfrommi.pandoc.types.Header
import com.github.dfrommi.pandoc.types.HorizontalRule
import com.github.dfrommi.pandoc.types.Para
import com.github.dfrommi.pandoc.types.Space
import com.github.dfrommi.pandoc.types.Str

import static org.junit.Assert.assertEquals


class PandocFilterSpec {
	def oldSystemIn = null
	def oldSystemOut = null
	
	PandocFilter filter = Pandoc.factory.filter
	PandocConverter converter = Pandoc.factory.converter
	
	def setup() {
		oldSystemIn = System.in
		oldSystemOut = System.out
	}
	
	def cleanup() {
		System.in = oldSystemIn
		System.out = oldSystemOut
	}
	
  def "toJSONFilter transformation replaces elements"() {
		performTransformTest("Hello world", "HELLO WORLD") {
			if(it in Str) {
				new Str(it.text.toUpperCase())
			}
		}
	}

  def "toJSONFilter transformation removes text elements"() {
		performTransformTest("Hello world", "Hello") {
			if((it in Str && it.text == "world") || it in Space)  {
				[]
			}
		}
	}

  def "toJSONFilter transformation removes elements"() {
		String input = '''
# Header

Some text.

-----

Some more text
'''
		
		String output = '''
# Header

Some text.

Some more text
'''
		performTransformTest(input, output) {
			if(it in HorizontalRule) {
				[]
			}
		}
	}

  def "toJSONFilter transformation adds elements"() {
		performTransformTest("Hello world", "Good morning world") {
			if (it in Str && it.text == "Hello")  {
				[new Str("Good"), new Space(), new Str("morning")]
			}
		}
	}

	private performTransformTest(String mdInput, String mdExpectedResult, Closure transformation) {
		String input = converter.mdToJsonText(mdInput)
		String expected = converter.mdToJsonText(mdExpectedResult)
		
		def outContent = hijackStreams(input)

		filter.toJSONFilter(transformation)
		
		String result = outContent.toString()
		assertEqualJson(expected, result)
	}

	
	
  def "toJSONFilter processes elements in correct order"() {
		performFlowTest("Hello world", [Para, Str, Space, Str])
		performFlowTest('''
# Header

Some paragraph

```
Some code
```
''', 
		[Header, Para, CodeBlock, Str /*from header*/, Str, Space, Str])
	}

	private performFlowTest(String mdInput, expectedSequence) {
		def classSequence = []
		
		String input = converter.mdToJsonText(mdInput)
		def outContent = hijackStreams(input)

		filter.toJSONFilter {
			classSequence << it.getClass()
			null
		}

		String result = outContent.toString()
		assertEqualJson(input, result)
		assertEquals(expectedSequence, classSequence)
	}

  def "toJSONFilter transformation copies elements"() {
      (1..12).each { i ->
			String mdInput = this.getClass().getResourceAsStream("/test-${i}.md").text
			String input = converter.mdToJsonText(mdInput)
			def outContent = hijackStreams(input)

			filter.toJSONFilter { elem ->
				elem
			}

			String result = outContent.toString()
			assertEqualJson(input, result)
		}
	}

	/**
	 * Assert same Json by parsing to data structure before comparison
	 * @param expected Expected json text
	 * @param actual Actual json text
	 */
	private assertEqualJson(String expected, String actual) {
		def expectedJson = converter.jsonTextToJson(expected)
		def actualJson = converter.jsonTextToJson(actual)
		
		assertEquals(expectedJson, actualJson)
	} 
	
		
	static private OutputStream hijackStreams(String inputText) {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream()
		ByteArrayInputStream inContent = new ByteArrayInputStream(inputText.getBytes())

		System.out = new PrintStream(outContent)
		System.in = inContent
		
		outContent
	}
}
