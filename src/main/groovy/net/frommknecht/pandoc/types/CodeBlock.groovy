package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Pandoc

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
