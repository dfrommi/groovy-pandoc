package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Cite.
 * 
 * @Example {@code Blah blah [see @doe99, pp. 33-35; also @smith04, ch. 1].}
 */
@Pandoc
class Cite implements Inline {
	/**
	 * Citation list
	 */
	@Child @JsonValue(index=1) 
	Citation[] citations = []
	/**
	 * The citation text
	 */
	@Child @JsonValue(index=2) 
	Inline[] text = []
}
