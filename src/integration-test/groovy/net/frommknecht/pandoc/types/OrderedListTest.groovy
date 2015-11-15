package net.frommknecht.pandoc.types;

import static org.junit.Assert.*;
import static net.frommknecht.pandoc.types.OrderedList.NumberStyle.*
import static net.frommknecht.pandoc.types.OrderedList.Delimiter.*

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

class OrderedListTest extends TypeTestBase {
	@Parameters
	static getParams() {
		[ testList('1.', '2.', Decimal, Period), 
		testList('i.', 'i.', LowerRoman, Period),
		testList('1)', '2)', Decimal, OneParen),
		testList('(1)', '(2)', Decimal, TwoParens),
		testList('3.', '4.', Decimal, Period, 3),
		]
	}
	
	
	private static testList(firstBullet, secondBullet, numberStyle, delimiter, startNum = 1) {
		[ 
"""
$firstBullet one
$secondBullet two
""",
			new OrderedList(startNum: startNum, numberStyle: numberStyle, delimiter: delimiter, items: [
				[new Plain(content: new Str("one"))],
				[new Plain(content: new Str("two"))]
			])] as Object[]
	}
}
