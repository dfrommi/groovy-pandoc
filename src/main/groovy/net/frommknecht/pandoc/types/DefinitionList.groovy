package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Definition list.
 * 
 * @Example <pre>{@code 
 *  head
 *    ~ value
 * }</pre>
 */
@Pandoc
class DefinitionList implements Block {
	/**
	 * List of definition items.
	 */
	@Child @JsonValue 
	DefinitionItem[] items = []
}
