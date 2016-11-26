package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Subscript text
 *
 * @Example {@code H~2~O is water.}
 */
@Pandoc(callSuper = true, includeSuper = true)
class Subscript extends PandocTextElement implements Inline {

}
