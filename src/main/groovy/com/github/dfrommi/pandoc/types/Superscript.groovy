package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Superscript
 *
 * @Example {@code 2^10^ is 1024.}
 */
@Pandoc(callSuper = true, includeSuper = true)
class Superscript extends PandocTextElement implements Inline {

}
