package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Metadata
 *
 * @Example <pre>{@code
 * ---
 * list:
 *  - a: 1
 *    b: 2
 * ---
 *}</pre>
 */
@Pandoc
class Meta {
  /**
   * Metadata structure (lists, maps etc.)
   */
  def metadata = [:]
}

