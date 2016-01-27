package net.frommknecht.pandoc.types

import org.junit.runners.Parameterized.Parameters

class ImageTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		['![caption](someimage.jpg "altText")',
		 new Image(title: [new Str("caption")], url: "someimage.jpg", altText: "fig:altText")] as Object[],

		['![caption](someimage.jpg "altText"){#id .class width=30 height=20px}',
		new Image(title: [new Str("caption")], url: "someimage.jpg", altText: "fig:altText",
				attr: new Attributes(identifier: 'id', classes: ['class'], properties: [width: '30', height: '20px']))] as Object[]
	]}
}
