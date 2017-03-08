#!/usr/bin/env groovy

@GrabResolver('https://jitpack.io')
@Grab('com.github.dfrommi:groovy-pandoc')

import com.github.dfrommi.pandoc.Pandoc
import com.github.dfrommi.pandoc.types.*
import groovy.text.SimpleTemplateEngine

import static com.github.dfrommi.pandoc.Pandoc.*

// This filter allows you to include other markdown files in
// your markdown. It supports an arbitrary level of inclusions, which means
// your includes can include markdown.
// The replaced text is interpreted as markdown.
// If the filename has 'gt' extension, the content is processed as Groovy template.

def converter = Pandoc.factory.converter

toJSONFilter(CodeBlock) { CodeBlock cb, meta ->
  def result = [cb]

  while (result.any {isIncludeBlock(it)}) {
    result = result.collectMany { isIncludeBlock(it) ? converter.mdToDocument(includeFiles(it.code))['blocks'] : [it] }
  }

  result
}

boolean isIncludeBlock(element) {
  element in CodeBlock && "include" in element.attr.classes
}

String includeFiles(lines) {
  lines.readLines().
    collect{ it.trim() }.findAll { it && !it.startsWith('#') }. // ignore empty and commented lines
    collect{ includeFile(it) }.
    join("\n")
}

String includeFile(String filename) {
  def content = new File(filename).text
	filename.endsWith('.gt') ?
		new SimpleTemplateEngine().createTemplate(content).make() :
		content
}
