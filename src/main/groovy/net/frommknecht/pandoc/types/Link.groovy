package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc

/**
 * A link.
 * 
 * @Example {@code [theText](/url 'title')}
 */
@Pandoc
class Link implements Inline {
	/**
	 * Link text.
	 */
	@Child @JsonValue(index=1) 
	Inline[] text = []
	
	/**
	 * The URL.
	 */
	@JsonValue(index=2, subindex=1)
	def url = ""
	
	/**
	 * Link title. 
	 */
	@JsonValue(index=2, subindex=2)
	def title = ""
}
