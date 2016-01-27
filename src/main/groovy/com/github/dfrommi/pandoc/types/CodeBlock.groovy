package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc

/**
 * Code block
 * 
 * @Example <pre>{@code
 * ```{#mycode .haskell .numberLines startFrom="100" endAt="200"}
 * Some code
 * ```
 * }</pre>
 */
@Pandoc
class CodeBlock extends CodeBase implements Block {

}
