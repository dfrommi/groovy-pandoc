package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Image reference
 *
 * @Example {@code ![caption](someimage.jpg "altText"){#id .class width=30px}}
 */
@Pandoc
class Image implements Inline {
  /**
   * The attributes
   */
  @JsonValue(index = 1)
  Attributes attr = new Attributes()

  /**
   * The title
   */
  @Child
  @JsonValue(index = 2)
  Inline[] title = []

  /**
   * The image URL
   */
  @JsonValue(index = 3, subindex = 1)
  def url = ""

  /**
   * Alternate text.
   *
   * Use prefix 'fig:' if image is in its own paragraph. Then altText becomes figure caption.
   */
  @JsonValue(index = 3, subindex = 2)
  def altText = ""
}
