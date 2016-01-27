package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class CiteTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		["Blah blah [see @doe99, pp. 33-35; also @smith04, ch. 1].",
		 new Cite(citations: [
		 	new Citation(
				id: "doe99",
				prefix: new Str("see").splitted(),
				suffix: new Str(", pp. 33-35").splitted()
			),
			new Citation(
				id: "smith04",
				prefix: new Str("also").splitted(),
				suffix: new Str(", ch. 1").splitted())],
		 	text: new Str("[see @doe99, pp. 33-35; also @smith04, ch. 1]").splitted()
			 )] as Object[]
	]}
}
