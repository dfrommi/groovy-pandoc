package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


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
