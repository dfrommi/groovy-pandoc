package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Raw inline content
 *
 * @Example {@code Some <svg>text</svg>}
 */
@Pandoc(callSuper = true, includeSuper = true)
class RawInline extends RawBase implements Inline {

}
