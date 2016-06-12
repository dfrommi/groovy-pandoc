package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class ImageSpec extends TypeSpecBase {
  @Shared
  def conversions = [
		['![caption](someimage.jpg "altText")',
		 new Image(title: [new Str("caption")], url: "someimage.jpg", altText: "fig:altText")],

		['![caption](someimage.jpg "altText"){#id .class width=30 height=20px}',
		new Image(title: [new Str("caption")], url: "someimage.jpg", altText: "fig:altText",
				attr: new Attributes(identifier: 'id', classes: ['class'], properties: [width: '30', height: '20px']))]
	]
}
