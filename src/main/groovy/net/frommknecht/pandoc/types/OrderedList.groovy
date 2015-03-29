package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Ordered list
 * 
 * @Example <pre>{@code 
 * 1. one
 * 2. two
 * }</pre>
 */
@Pandoc
class OrderedList implements Block {
	/**
	 * Numbering style types
	 */
	enum NumberStyle {
		DefaultStyle,
		Example,
		Decimal,
		LowerRoman,
		UpperRoman,
		LowerAlpha,
		UpperAlpha
	}

	/**
	 * Delimiter types
	 */
	enum Delimiter {
		DefaultDelim,
		Period,
		OneParen,
		TwoParens
	}

	/**
	 * Numbering start number.
	 */
	@JsonValue(index=1, subindex=1)
	int startNum = 1
	
	/**
	 * Number style 
	 */
	@JsonValue(index=1, subindex=2)
	NumberStyle numberStyle = NumberStyle.DefaultStyle
	
	/**
	 * Delimiter style 
	 */
	@JsonValue(index=1, subindex=3)
	Delimiter delimiter = Delimiter.DefaultDelim

	/**
	 * List of items, each item a list of Block elements.
	 */
	@Child @JsonValue(index=2) 
	Block[][] items = [][]
}
