package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.JsonValue


/**
 * Common base class for raw elements.
 */
abstract class RawBase implements PandocType {
	/**
	 * Raw content format.
	 */
	@JsonValue(index=1)
	String format = "html"
	
	/**
	 * The raw content. 
	 */
	@JsonValue(index=2)
	String content = ""
}
