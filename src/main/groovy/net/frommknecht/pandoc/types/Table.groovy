package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Table structure.
 *
 * @Example <pre>{@code
 *   Right   Left     Center     Default
 * -------   ------ ----------   -------
 *    12   12        12            12
 *   123   123       123          123
 *     1   1          1             1
 *
 * Table: Caption
 * }</pre>
 */
@Pandoc
class Table implements Block {
	/**
	 * Column alignment styles.
	 */
	enum Alignment {
		AlignLeft,
		AlignRight,
		AlignCenter,
		AlignDefault
	}
	
	/**
	 * Table caption
	 */
	@Child @JsonValue(index=1) 
	Inline[] caption = []
	
	/**
	 * Alignments per column
	 */
	@JsonValue(index=2)
	Alignment[] alignments = []
	
	/**
	 * Width per column (relative)
	 */
	@JsonValue(index=3)
	BigDecimal[] widths = []
	
	/**
	 * List of headers, each header a list of Block elements.
	 */
	@Child @JsonValue(index=4)  
	Block[][] headers = [][]
	
	/**
	 * Table content.
	 * 
	 * List of rows. Each row a list of cells. Each cell a list of Blocks.
	 */
	@Child @JsonValue(index=5)  
	Block[][][] content = [][][]

	/**
	 * Gets widths array.
	 * @return Widths array
	 */
	public BigDecimal[] getWidths() {
		return widths.size() > 0 ? widths : [0] * guessColumnCount()
	}

	/**
	 * Sets widths array
	 * @param widths Width array
	 */
	public void setWidths(BigDecimal[] widths) {
		this.widths = (widths.every{it == 0}) ? [] : widths
	}

	/**
	 * Guess table column count.
	 * @return The column count
	 */
	private guessColumnCount() {
		content.size() > 0 ? content[0].size() : 0 
	}
}
