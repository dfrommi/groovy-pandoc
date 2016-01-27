package com.github.dfrommi.pandoc.types

import org.junit.runners.Parameterized.Parameters;
import static com.github.dfrommi.pandoc.types.Table.Alignment.*

class TableTest extends TypeTestBase {
	@Parameters
	static getParams() { [
		['''
  Right   Left     Center     Default
-------   ------ ----------   -------
     12   12        12            12
    123   123       123          123
      1   1          1             1

Table: Caption
''',
			new Table(alignments: [AlignRight, AlignLeft, AlignCenter, AlignDefault],
				caption: [new Str("Caption")],
				//widths:  [0,0,0,0],
				headers: [[cs("Right")], [cs("Left")], [cs("Center")], [cs("Default")]],
				content: [[cs("12"), cs("12"), cs("12"), cs("12")],
						  [cs("123"), cs("123"), cs("123"), cs("123")],
						  [cs("1"), cs("1"), cs("1"), cs("1")]
			  ])
		] as Object[]
	] }
	
	// Cell String
	static cs(value) {
		new Plain(content: [new Str(value)])
	}
}
