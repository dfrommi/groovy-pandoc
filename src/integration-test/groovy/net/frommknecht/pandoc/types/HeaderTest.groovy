package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class HeaderTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		["## Headline",
		 new Header(level: 2, attr: new Attributes(identifier: "headline"), text: [new Str("Headline")])	
		] as Object[]
	]}
}
