package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters


class LineBreakTest extends TypeTestBase {
	@Parameters //Newline not working in Eclipse
	static getParams() { [
		["First  \nsecond", new LineBreak()] as Object[]
	]}
}
