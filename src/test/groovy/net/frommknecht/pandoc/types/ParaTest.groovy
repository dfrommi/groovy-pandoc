package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class ParaTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Text',
			 new Para(content: new Str("Text"))] as Object[]
		]
	}
}
