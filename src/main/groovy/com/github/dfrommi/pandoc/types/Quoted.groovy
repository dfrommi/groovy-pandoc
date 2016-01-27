package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Quoted text
 */
@Pandoc
class Quoted implements Inline {
	/**
	 * Quotation types
	 */
	enum QuoteType {
		SingleQuote,
		DoubleQuote
	}

	/**
	 * Quotation type
	 */
	@JsonValue(index=1)	
	QuoteType type = QuoteType.SingleQuote
	
	/**
	 * Quoted text 
	 */
	@Child	@JsonValue(index=2)	
	Inline[] text = []
}
