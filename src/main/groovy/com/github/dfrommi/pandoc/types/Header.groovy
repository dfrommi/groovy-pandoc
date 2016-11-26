package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Header
 *
 * @Example {@code # # The header}
 */
@Pandoc
class Header implements Block {
  /**
   * Header level
   */
  @JsonValue(index = 1)
  Integer level = 0

  /**
   * Header attributes.
   */
  @JsonValue(index = 2)
  Attributes attr = new Attributes()

  /**
   * Header text
   */
  @Child
  @JsonValue(index = 3)
  Inline[] text = []
}
