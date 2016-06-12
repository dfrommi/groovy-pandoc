package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Common base class for raw elements.
 */
@Pandoc
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
