package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class HorizontalRuleTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['* * * * *', new HorizontalRule()] as Object[],
			['--------', new HorizontalRule()] as Object[],
		]
	}
}
