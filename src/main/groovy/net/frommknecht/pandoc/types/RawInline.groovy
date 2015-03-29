package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Raw inline content
 *
 * @Example {@code Some <svg>text</svg>}
 */
@Pandoc
class RawInline extends RawBase implements Inline {

}
