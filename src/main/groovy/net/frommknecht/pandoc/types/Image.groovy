package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Image reference
 * 
 * @Example {@code ![caption](someimage.jpg "altText")}
 */
@Pandoc
class Image implements Inline {
	/**
	 * The title
	 */
	@Child @JsonValue(index=1) 
	Inline[] title = []

	/**
	 * The image URL
	 */
	@JsonValue(index=2, subindex=1)
	def url = ""
	
	/**
	 * Alternate text. 
	 * 
	 * Use prefix 'fig:' if image is in its own paragraph. Then altText becomes figure caption.  
	 */
	@JsonValue(index=2, subindex=2)
	def altText = ""
}
