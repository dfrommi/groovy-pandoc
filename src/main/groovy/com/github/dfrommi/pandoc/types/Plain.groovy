package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


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
