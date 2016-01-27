package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters

class MathTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
			['Here some inline $math$ stuff',
			 new Math(inline: true, math: 'math')] as Object[],
			['$$display math$$',
			 new Math(inline: false, math: 'display math')] as Object[]
		] 
	}
}
