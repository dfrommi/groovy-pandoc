package net.frommknecht.pandoc.types;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

class BlockQuoteTest extends TypeTestBase {
	@Parameters
	static getParams() { [
		['> aBlockQuote',
		 new BlockQuote(quote: [new Para(content: new Str("aBlockQuote"))])] as Object[]
	] }

	@Test
	void getChildrenTest() {
		def bq = new BlockQuote(quote: [new Para(content: new Str("aBlockQuote"))])
		assert bq.children.containsKey('quote')
		assert bq.quote in Block[]
	}
	
}
