package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


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
