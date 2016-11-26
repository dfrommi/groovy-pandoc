package com.github.dfrommi.pandoc.convert

import com.github.dfrommi.pandoc.types.Block
import com.github.dfrommi.pandoc.types.Inline
import com.github.dfrommi.pandoc.types.PandocType
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.util.AnnotatedFieldInfo
import com.github.dfrommi.pandoc.util.AnnotationHelper


/**
 * Converter between json structure and pandoc type objects.
 */
class JsonToPandocTypeConverter implements JsonTypeConverter {
  /* (non-Javadoc)
     * @see JsonTypeConverter#fromJson(java.lang.Object)
     */

  def fromJson(json) {
    def (type, value) = [json.t, json.c]
    def fullType = PandocType.class.package.name + "." + type
    PandocType instance = PandocType.class.classLoader.loadClass(fullType, true)?.newInstance()
    initObjectFromJsonValue(instance, value)
    instance
  }

  /* (non-Javadoc)
     * @see JsonTypeConverter#toJson(java.lang.Object)
     */

  def toJson(obj) {
    def value = getJsonValue(obj)
    return value ?
      [c: value, t: getJsonType(obj)] :
      [t: getJsonType(obj)]
  }

  /**
   * Get the json type from object.
   *
   * @param object the object
   * @return the json type
   */
  protected String getJsonType(object) {
    object.getClass().simpleName
  }

  /**
   * Initializes an Pandoc type object from json value data using annotations.
   *
   * @param object the target object
   * @param jsonValue the json value
   * @return the initialized target object
   */
  protected initObjectFromJsonValue(object, jsonValue) {
    AnnotationHelper.findAllFieldsWithAnnotation(object.class, JsonValue).each { AnnotatedFieldInfo<JsonValue> afi ->
      def property = object[afi.name]
      def input = (afi.annotation.index() == 0) ? jsonValue : jsonValue[afi.annotation.index() - 1]
      if (afi.annotation.subindex() > 0) {
        input = input[afi.annotation.subindex() - 1]
      } else if (afi.annotation.key()) {
        input = input[afi.annotation.key()]
      }

      def newValue
      if (afi.annotation.fromJson() in Closure) {
        def closure = afi.annotation.fromJson().newInstance(null, null)
        newValue = closure.call(input)
      } else {
        newValue = convertJsonToInt(property.class, input)
      }

      object[afi.name] = newValue
    }
  }

  /**
   * Gets json value from object.
   *
   * @param object the source object
   * @return Json value
   */
  protected getJsonValue(object) {
    def value = []
    AnnotationHelper.findAllFieldsWithAnnotation(object.class, JsonValue).each { AnnotatedFieldInfo<JsonValue> afi ->
      def property = object[afi.name]
      def index = afi.annotation.index() - 1
      def subindex = afi.annotation.subindex() - 1
      def key = afi.annotation.key()

      def newValue

      if (afi.annotation.toJson() in Closure) {
        def closure = afi.annotation.toJson().newInstance(null, null)
        newValue = closure.call(property)
      } else {
        newValue = convertIntToJson(property)
      }

      value = assignNewValue(value, newValue, index, subindex, key)
    }
    value
  }

  /**
   * Assign a new value to structure.
   *
   * @param value the existing value structure
   * @param newValue the value to be assigned
   * @param index the array index
   * @param subindex the second level array index
   * @param key the map key
   * @return the values object with newValue assigned
   */
  protected assignNewValue(value, newValue, int index, int subindex, String key) {
    if (index >= 0) {
      if (!(value in List)) {
        value = []
      }
      value[index] = assignNewValue(value[index], newValue, -1, subindex, key)
    } else if (subindex >= 0) {
      if (!(value in List)) {
        value = []
      }
      value[subindex] = assignNewValue(value[subindex], newValue, index, -1, key)
    } else if (key) {
      if (!(value in Map)) {
        value = [:]
      }
      value[key] = assignNewValue(value[key], newValue, index, subindex, "")
    } else {
      value = newValue
    }
    value
  }

  /**
   * Converts json structure to internal object
   * @param targetType the target object type
   * @param input the json input
   * @return the new value of target type
   */
  protected convertJsonToInt(Class targetType, input) {
    switch (targetType) {
      case Object[]:
        return input.collect { convertJsonToInt(targetType.getComponentType(), it) }
      case Inline:
      case Block:
        return fromJson(input)
      case PandocType:
        PandocType newInstance = targetType.newInstance()
        initObjectFromJsonValue(newInstance, input)
        return newInstance
      case Enum:
        return targetType.valueOf(input.t)
      default:
        return input
    }
  }

  /**
   * Converts internal object value to json.
   *
   * @param property the target property.
   * @return the json structure
   */
  protected convertIntToJson(property) {
    switch (property) {
      case Object[]:
        return property.collect { convertIntToJson(it) }
      case Inline:
      case Block:
        return toJson(property)
      case PandocType:
        return getJsonValue(property)
      case Enum:
        return [t: property as String]
      default:
        return property
    }
  }
}
