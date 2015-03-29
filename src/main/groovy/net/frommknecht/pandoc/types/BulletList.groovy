package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Bullet list.
 * 
 * @Example <pre>{@code 
 * - one
 * - two
 * }</pre>
 */
@Pandoc
class BulletList implements Block {
	/**
	 * List of items, each item a list of Blocks.
	 */
	@Child
	@JsonValue
	Block[][] items = [][]
}
