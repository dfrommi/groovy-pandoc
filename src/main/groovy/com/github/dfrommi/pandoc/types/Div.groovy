package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc

/**
 * Div element
 *
 * @Example <pre>{@code
 * <div class="one two" id="myId" key="value">
 * TheContent
 * </div>
 *}</pre>
 */
@Pandoc
class Div implements Block {
  /**
   * Attributes
   */
  @JsonValue(index = 1)
  Attributes attr = new Attributes()
  /**
   * Div content
   */
  @Child
  @JsonValue(index = 2)
  Block[] content = []
}
