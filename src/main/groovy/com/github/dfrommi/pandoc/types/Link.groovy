package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc

/**
 * A link.
 * 
 * @Example {@code [theText](/url 'title')}
 */
@Pandoc
class Link implements Inline {
	/**
	 * The attributes
	 */
	@JsonValue(index=1)
	Attributes attr = new Attributes()

	/**
	 * Link text.
	 */
	@Child @JsonValue(index=2)
	Inline[] text = []
	
	/**
	 * The URL.
	 */
	@JsonValue(index=3, subindex=1)
	def url = ""
	
	/**
	 * Link title. 
	 */
	@JsonValue(index=3, subindex=2)
	def title = ""
}
