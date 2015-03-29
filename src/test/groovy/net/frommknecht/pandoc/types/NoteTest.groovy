package net.frommknecht.pandoc.types;

import static org.junit.Assert.*

import org.junit.runners.Parameterized.Parameters

class NoteTest extends TypeTestBase {
	@Parameters
	static getParams() {[
		["Some ref [^1]. Bla.\n\n[^1]: Footnote",
		new Note(content: [new Para(content: [new Str("Footnote")])])] as Object[]	
	]}
}
