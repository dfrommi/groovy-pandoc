package net.frommknecht.pandoc.types;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

class DivTest extends TypeTestBase {
	@Parameters
	static getParams() { [
		['''
<div class="one two" id="myId" key="value">
TheContent
</div>
''',
			new Div(attr: new Attributes(identifier: "myId", classes: ["one", "two"], properties: [key: "value"]),
				content: [new Para(content: [new Str("TheContent")])])
				
		] as Object[]
	] }
}
