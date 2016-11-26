package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Strikeout text
 *
 * @Example {@code The Text is ~~deleted~~.}
 */
@Pandoc(callSuper = true, includeSuper = true)
class Strikeout extends PandocTextElement implements Inline {

}
