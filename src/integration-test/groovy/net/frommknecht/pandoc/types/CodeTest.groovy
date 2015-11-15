package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class CodeTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		['This is `Some code`{#mycode .haskell .numberLines startFrom="100" endAt="200"}',
		 new Code(code: "Some code", attr: new Attributes(identifier: "mycode", classes: ["haskell", "numberLines"],
			properties: [startFrom: "100", endAt: "200"]))] as Object[]
	] }
}
