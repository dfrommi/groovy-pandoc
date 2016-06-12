package com.github.dfrommi.pandoc.types

import spock.lang.Shared

class NoteSpec extends TypeSpecBase {
  @Shared
  def conversions = [
		["Some ref [^1]. Bla.\n\n[^1]: Footnote",
		new Note(content: [new Para(content: [new Str("Footnote")])])]
	]
}
