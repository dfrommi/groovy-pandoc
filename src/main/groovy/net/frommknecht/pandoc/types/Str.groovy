package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * String
 * 
 * @Example {@code Any text}
 */
@Pandoc
class Str implements Inline {
	/**
	 * The text
	 */
	@JsonValue
	String text = ""

	/**
	 * Transforms Str with spaces into list of Str and Space elements. 
	 * @return List of Str and Space elements.
	 */
	def splitted() {
		def splitList = []
		text.split(" ").each{
			splitList << new Str(it)
			splitList << new Space()
		}
		splitList[0..-2]
	}
}
