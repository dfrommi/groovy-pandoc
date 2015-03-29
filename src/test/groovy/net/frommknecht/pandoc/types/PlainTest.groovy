package net.frommknecht.pandoc.types;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

class PlainTest extends TypeTestBase {
	@Parameters
	static getParams() { [
		['* PlainText',
			new Plain(content: [new Str("PlainText")])
		] as Object[]
	] }

}
