package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.Ignore
import org.junit.runners.Parameterized.Parameters

@Ignore("Find way to create Quoted from markdown.")
class QuotedTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		["Hallo", new Quoted(type: Quoted.QuoteType.SingleQuote, text: [new Str("Hallo")])] as Object[]
	]}
}
