package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.Ignore
import org.junit.runners.Parameterized.Parameters


class LineBreakTest extends TypeTestBase {
	@Parameters //Newline not working in Eclipse
	static getParams() { [
		["First  \nsecond", new LineBreak()] as Object[]
	]}
}
