package com.github.dfrommi.pandoc

import com.github.dfrommi.pandoc.convert.PandocConverter

import org.junit.Ignore
import org.junit.Test

class PandocConverterTest {

	@Test
	@Ignore("TODO")
	void testMethodMissing() {
		PandocConverter conv = new PandocConverter()
		def result = conv.mdToJson("Hello world")
		println result
		
		def obj = conv.mdToObject("Hello *world*")
		println obj
		println conv.objectToMd(obj)
		
		conv.objectToSomething("hallo")
	}
}
