package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue


/**
 * Base class for text elements.
 */
abstract class PandocTextElement implements PandocType {
	/**
	 * The text.
	 */
	@Child @JsonValue
	Inline[] text = []
}
