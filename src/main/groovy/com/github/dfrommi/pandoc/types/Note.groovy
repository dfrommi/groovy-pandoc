package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


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
