package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Block Quote.
 *
 * @Example {@code > aBlockQuote}
 */
@Pandoc
class BlockQuote implements Block {
  /**
   * The quote content.
   */
  @Child
  @JsonValue
  Block[] quote = []
}
