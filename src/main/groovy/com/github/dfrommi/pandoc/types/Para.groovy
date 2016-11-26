package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Paragraph.
 */
@Pandoc
class Para implements Block {
  /**
   * Paragraph content.
   */
  @Child
  @JsonValue
  Inline[] content = []
}
