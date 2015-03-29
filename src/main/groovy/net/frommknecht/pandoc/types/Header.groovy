package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Header
 * 
 * @Example {@code ## The header}
 */
@Pandoc
class Header implements Block {
	/**
	 * Header level 
	 */
	@JsonValue(index=1)
	Integer level = 0
	
	/**
	 * Header attributes. 
	 */
	@JsonValue(index=2)
	Attributes attr = new Attributes()
	 
	/**
	 * Header text 
	 */
	@Child @JsonValue(index=3)
	Inline[] text = []
}
