package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Plain items.
 */
@Pandoc
class Plain implements Block {
	/**
	 * Content
	 */
	@Child @JsonValue
	Inline[] content = []
}
