package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue


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
