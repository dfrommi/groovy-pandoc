package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class SmallCapsTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Here are <span style="font-variant:small-caps;">smallcaps</span>',
			 new SmallCaps(text: [new Str("smallcaps")])] as Object[]
		]
	}
}
