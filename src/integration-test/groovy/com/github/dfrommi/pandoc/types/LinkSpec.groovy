package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class LinkSpec extends TypeSpecBase {
  @Shared
  def conversions = [
    ["<http://google.com>",
     new Link(url: "http://google.com", text: [new Str("http://google.com")])],

    ["[theText](/url)",
     new Link(url: "/url", text: [new Str("theText")])],

    ["[theText](/url 'title')",
     new Link(url: "/url", text: [new Str("theText")], title: "title")],

    ["[theText](/url 'title'){#id .class width=30px}",
     new Link(url: "/url", text: [new Str("theText")], title: "title",
       attr: new Attributes(identifier: 'id', classes: ['class'], properties: [width: '30px']))]
  ]
}
