package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Strong text
 * 
 * @Example {@code The Text is **strong**.}
 */
@Pandoc(callSuper = true, includeSuper = true)
class Strong extends PandocTextElement implements Inline {

}
