package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc

/**
 * Emphasized text.
 * 
 * @Example {@code Some *emph* text.}
 */
@Pandoc
class Emph extends PandocTextElement implements Inline {
}