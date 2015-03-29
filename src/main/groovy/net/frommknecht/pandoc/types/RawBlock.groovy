package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Pandoc


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
