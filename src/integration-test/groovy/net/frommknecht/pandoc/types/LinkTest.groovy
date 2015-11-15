package net.frommknecht.pandoc.types;

import static org.junit.Assert.*
import groovy.transform.InheritConstructors;

import org.junit.runners.Parameterized.Parameters


class LinkTest extends TypeTestBase {
	@Parameters(name = "{index}: {0}")
	static getParams() { [
		["<http://google.com>",
		new Link(url: "http://google.com", text: [new Str("http://google.com")])] as Object[],
	
		["[theText](/url)",
		new Link(url: "/url", text: [new Str("theText")])] as Object[],
	
		["[theText](/url 'title')",
		new Link(url: "/url", text: [new Str("theText")], title: "title")] as Object[]
	]}
}
