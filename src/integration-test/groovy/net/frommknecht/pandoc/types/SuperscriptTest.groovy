package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class SuperscriptTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['2^10^ is 1024.',
			 new Superscript(text: [new Str("10")])] as Object[]
		]
	}
}
