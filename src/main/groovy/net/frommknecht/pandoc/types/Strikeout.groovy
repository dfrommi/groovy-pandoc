package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Strikeout text
 * 
 * @Example {@code The Text is ~~deleted~~.}
 */
@Pandoc
class Strikeout extends PandocTextElement implements Inline {

}
