package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Inline code.
 * 
 * @Example {@code This is `Some code`{#mycode .haskell .numberLines startFrom="100" endAt="200"}}
 */
@Pandoc
class Code extends CodeBase implements Inline {
}
