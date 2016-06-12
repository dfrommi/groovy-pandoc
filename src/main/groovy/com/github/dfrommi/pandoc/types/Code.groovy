package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Inline code.
 * 
 * @Example {@code This is `Some code`{#mycode .haskell .numberLines startFrom="100" endAt="200"}}
 */
@Pandoc(callSuper = true, includeSuper = true)
class Code extends CodeBase implements Inline {
}
