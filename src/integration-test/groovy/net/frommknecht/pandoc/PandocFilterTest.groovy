package net.frommknecht.pandoc;

import static org.junit.Assert.*
import net.frommknecht.pandoc.convert.PandocConverter
import net.frommknecht.pandoc.types.CodeBlock
import net.frommknecht.pandoc.types.Header
import net.frommknecht.pandoc.types.HorizontalRule
import net.frommknecht.pandoc.types.Para
import net.frommknecht.pandoc.types.Space
import net.frommknecht.pandoc.types.Str

import org.junit.After
import org.junit.Before
import org.junit.Test


class PandocFilterTest {
	def oldSystemIn = null
	def oldSystemOut = null
	
	PandocFilter filter = Pandoc.factory.filter
	PandocConverter converter = Pandoc.factory.converter
	
	@Before
	void setUpStreams() {
		oldSystemIn = System.in
		oldSystemOut = System.out
	}
	
	@After
	void restoreStreams() {
		System.in = oldSystemIn
		System.out = oldSystemOut
	}
	
	@Test
	public void testToJSONFilterTransformReplaceElements() {
		performTransformTest("Hello world", "HELLO WORLD") {
			if(it in Str) {
				new Str(it.text.toUpperCase())
			}
		}
	}

	@Test
	public void testToJSONFilterTransformRemoveElement() {
		performTransformTest("Hello world", "Hello") {
			if((it in Str && it.text == "world") || it in Space)  {
				[]
			}
		}
	}

	@Test
	public void testToJSONFilterTransformRemoveElement2() {
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

	@Test
	public void testToJSONFilterTransformAddElements() {
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

	
	
	@Test
	public void testToJSONFilterFlow() {
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
	
		
	private OutputStream hijackStreams(String inputText) {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream()
		ByteArrayInputStream inContent = new ByteArrayInputStream(inputText.getBytes())

		System.out = new PrintStream(outContent)
		System.in = inContent
		
		outContent
	}
}
