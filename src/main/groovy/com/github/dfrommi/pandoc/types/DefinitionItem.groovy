package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc

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
