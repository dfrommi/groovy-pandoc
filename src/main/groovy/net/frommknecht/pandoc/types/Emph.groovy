package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Pandoc

/**
 * Emphasized text.
 * 
 * @Example {@code Some *emph* text.}
 */
@Pandoc
class Emph extends PandocTextElement implements Inline {
}