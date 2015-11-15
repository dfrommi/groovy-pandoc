package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class SubscriptTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['H~2~O is water.',
			 new Subscript(text: [new Str("2")])] as Object[]
		]
	}
}
