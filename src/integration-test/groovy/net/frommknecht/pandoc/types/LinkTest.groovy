package net.frommknecht.pandoc.types

import org.junit.runners.Parameterized.Parameters

class LinkTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		["<http://google.com>",
		new Link(url: "http://google.com", text: [new Str("http://google.com")])] as Object[],
	
		["[theText](/url)",
		new Link(url: "/url", text: [new Str("theText")])] as Object[],
	
		["[theText](/url 'title')",
		new Link(url: "/url", text: [new Str("theText")], title: "title")] as Object[],

		["[theText](/url 'title'){#id .class width=30px}",
		 new Link(url: "/url", text: [new Str("theText")], title: "title",
		 	attr: new Attributes(identifier: 'id', classes: ['class'], properties: [width: '30px']))] as Object[]
	]}
}
