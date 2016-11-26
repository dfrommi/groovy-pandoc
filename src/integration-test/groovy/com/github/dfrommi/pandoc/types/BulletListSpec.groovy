package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class BulletListSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    [
//'''
//* one
//* two
//''',
//		new BulletList(items: [
//			[new Plain(content: new Str("one"))],
//			[new Plain(content: new Str("two"))]
//		])], [
//'''
//- one
//- two
//''',
//			new BulletList(items: [
//				[new Plain(content: new Str("one"))],
//				[new Plain(content: new Str("two"))]
//			])], [
'''
- one
    - two
''',
new BulletList(items: [
  [new Plain(content: new Str("one")),
   new BulletList(items: [
     [new Plain(content: new Str("two"))]

   ])]
])]
  ]
}
