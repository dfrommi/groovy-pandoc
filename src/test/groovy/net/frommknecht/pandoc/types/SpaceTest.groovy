package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class SpaceTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Text with spaces', new Space()] as Object[]
		]
	}
}
