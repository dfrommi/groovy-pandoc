package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Base class for text elements.
 */
@Pandoc
abstract class PandocTextElement implements PandocType {
  /**
   * The text.
   */
  @Child
  @JsonValue
  Inline[] text = []
}
