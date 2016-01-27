package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc

/**
 * Common Code base class.
 */
@Pandoc
abstract class CodeBase implements PandocType {
	/**
	 * Attributes 
	 */
	@JsonValue(index=1)
	Attributes attr = new Attributes()
	
	/**
	 * The code
	 */
	@JsonValue(index=2)
	String code = ""
}
