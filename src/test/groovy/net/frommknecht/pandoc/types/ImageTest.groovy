package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class ImageTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		['![caption](someimage.jpg "altText")',
		 new Image(title: [new Str("caption")], url: "someimage.jpg", altText: "altText")] as Object[]
	]}
}
