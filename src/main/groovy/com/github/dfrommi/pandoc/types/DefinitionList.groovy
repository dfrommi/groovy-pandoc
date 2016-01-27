package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


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
