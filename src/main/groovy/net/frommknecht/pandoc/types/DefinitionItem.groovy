package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc

/**
 * Definition Items, used by DefinitionList.
 */
@Pandoc
class DefinitionItem implements PandocType {
	/**
	 * The definition term.
	 */
	@Child @JsonValue(index=1) 
	Inline[] term = []
	
	/**
	 * Definitions. 
	 */
	@Child @JsonValue(index=2)
	Block[][] definitions = [][]
}
