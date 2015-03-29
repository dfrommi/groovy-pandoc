package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * A note, like footnote.
 * 
 * @Example <pre>{@code 
 * Some ref [^1]. Bla.
 * 
 * [^1]: Footnote
 * }</pre>
 */
@Pandoc
class Note implements Inline {
	/**
	 * Note content.
	 */
	@Child @JsonValue 
	Block[] content = []
}
