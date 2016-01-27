package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Block of raw content.
 * 
 * @Example <pre>{@code 
 * Some text 
 * 
 * <svg>text</svg>
 * }</pre>
 */
@Pandoc
class RawBlock extends RawBase implements Block {

}
