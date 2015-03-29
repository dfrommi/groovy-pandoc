package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Math formula.
 * 
 * @Example <code>Here some inline $math$ stuff</code>
 */
@Pandoc
class Math implements Inline {
	/**
	 * Inline math (true) or block math (false) 
	 */
	@JsonValue(index=1, toJson={[t: it ? "InlineMath" : "DisplayMath", c:[]]}, fromJson={it.t == "InlineMath"})
	boolean inline = true
	
	/**
	 * Math formula
	 */
	@JsonValue(index=2)
	String math = ""
}
