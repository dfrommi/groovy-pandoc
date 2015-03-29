package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Block Quote.
 * 
 * @Example {@code > aBlockQuote}
 */
@Pandoc
class BlockQuote implements Block {
	/**
	 * The quote content.
	 */
	@Child 
	@JsonValue
	Block[] quote = []
}
