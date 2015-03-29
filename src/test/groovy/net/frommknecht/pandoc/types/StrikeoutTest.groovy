package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class StrikeoutTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['The Text is ~~deleted~~.',
			 new Strikeout(text: [new Str("deleted")])] as Object[]
		]
	}
}
