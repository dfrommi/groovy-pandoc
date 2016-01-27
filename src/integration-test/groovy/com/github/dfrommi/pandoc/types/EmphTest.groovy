package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class EmphTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		["Some *emph* text.",
		 new Emph(text: [new Str("emph")])] as Object[]
	]}
	
}
