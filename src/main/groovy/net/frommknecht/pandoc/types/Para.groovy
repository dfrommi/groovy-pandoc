package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Paragraph.
 */
@Pandoc
class Para implements Block {
	/**
	 * Paragraph content.
	 */
	@Child @JsonValue
	Inline[] content = []
}