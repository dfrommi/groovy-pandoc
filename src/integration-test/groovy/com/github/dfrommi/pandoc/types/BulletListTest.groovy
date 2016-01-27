package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters;

class BulletListTest extends TypeTestBase {
	@Parameters
	static getParams() { [
		[
'''
* one
* two
''',
		new BulletList(items: [
			[new Plain(content: new Str("one"))],
			[new Plain(content: new Str("two"))]
		])] as Object[], [
'''
- one
- two
''',
			new BulletList(items: [
				[new Plain(content: new Str("one"))],
				[new Plain(content: new Str("two"))]
			])] as Object[]
	] }
}
