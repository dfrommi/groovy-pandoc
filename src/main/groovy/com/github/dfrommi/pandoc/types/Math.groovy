package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


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
	@JsonValue(index=1, toJson={[t: it ? "InlineMath" : "DisplayMath"]}, fromJson={it.t == "InlineMath"})
	boolean inline = true
	
	/**
	 * Math formula
	 */
	@JsonValue(index=2)
	String math = ""
}
