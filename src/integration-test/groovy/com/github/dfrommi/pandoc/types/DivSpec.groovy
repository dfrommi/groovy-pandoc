package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class DivSpec extends TypeSpecBase {
  @Shared
  def conversions = [
		['''
<div class="one two" id="myId" key="value">
TheContent
</div>
''',
			new Div(attr: new Attributes(identifier: "myId", classes: ["one", "two"], properties: [key: "value"]),
				content: [new Para(content: [new Str("TheContent")])])
				
		]
	]
}
