package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class EmphTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		["Some *emph* text.",
		 new Emph(text: [new Str("emph")])] as Object[]
	]}
	
}
