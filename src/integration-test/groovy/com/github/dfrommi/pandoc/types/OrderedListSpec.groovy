package com.github.dfrommi.pandoc.types

import spock.lang.Shared

import static com.github.dfrommi.pandoc.types.OrderedList.Delimiter.*
import static com.github.dfrommi.pandoc.types.OrderedList.NumberStyle.Decimal
import static com.github.dfrommi.pandoc.types.OrderedList.NumberStyle.LowerRoman

class OrderedListSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    testList('1.', '2.', Decimal, Period),
    testList('i.', 'i.', LowerRoman, Period),
    testList('1)', '2)', Decimal, OneParen),
    testList('(1)', '(2)', Decimal, TwoParens),
    testList('3.', '4.', Decimal, Period, 3)
  ]

  private static testList(firstBullet, secondBullet, numberStyle, delimiter, startNum = 1) {
    [
      """
$firstBullet one
$secondBullet two
""",
      new OrderedList(startNum: startNum, numberStyle: numberStyle, delimiter: delimiter, items: [
        [new Plain(content: new Str("one"))],
        [new Plain(content: new Str("two"))]
      ])]
  }
}
