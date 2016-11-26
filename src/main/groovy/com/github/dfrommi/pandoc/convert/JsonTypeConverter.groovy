package com.github.dfrommi.pandoc.convert

/**
 * JSON type converter
 */
interface JsonTypeConverter {
  /**
   * Json to target object
   * @param json json structure
   * @return the target object
   */
  def fromJson(json)

  /**
   * Target object to json
   * @param object the object
   * @return json structure
   */
  def toJson(object)
}
