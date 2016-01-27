package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Image reference
 * 
 * @Example {@code ![caption](someimage.jpg "altText"){#id .class width=30px}}
 */
@Pandoc
class Image implements Inline {
	/**
	 * The attributes
	 */
	@JsonValue(index=1)
	Attributes attr = new Attributes()

	/**
	 * The title
	 */
	@Child @JsonValue(index=2)
	Inline[] title = []

	/**
	 * The image URL
	 */
	@JsonValue(index=3, subindex=1)
	def url = ""
	
	/**
	 * Alternate text. 
	 * 
	 * Use prefix 'fig:' if image is in its own paragraph. Then altText becomes figure caption.  
	 */
	@JsonValue(index=3, subindex=2)
	def altText = ""
}
