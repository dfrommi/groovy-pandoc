package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.Pandoc
import com.github.dfrommi.pandoc.convert.PandocConverter
import spock.lang.Specification

class TypeSpecBase extends Specification {
  PandocConverter converter = Pandoc.factory.converter

  def "initialization from JSON string creates expected object"() {
    given:
    def c = conversions
    def json = getJsonElement(md, obj)

    when:
    def newObject = converter.jsonToElement(json)

    then:
    obj == newObject

    where:
    [md, obj] << conversions
  }

  def "serialization of object creates expected JSON"() {
    given:
    def jsonResult = converter.elementToJson(obj)

    when:
    def expectedJson = getJsonElement(md, obj)

    then:
    expectedJson == jsonResult

    where:
    [md, obj] << conversions
  }


  def getJsonElement(md, obj) {
    def document = converter.mdToJson(md as String)
    findJsonElement(getJsonType(obj), document)
  }

  def getJsonType(object) {
    object.getClass().simpleName as String
  }

  def findJsonElement(type, node) {
    if (node in Map && node.t == type) {
      return node
    } else if (node in List || node in Map) {
      node.findResult {
        //println "X " + it + " -- " + it.getClass()
        if (it in Map && it.t == type) {
          it
        } else {
          if (it in Map || it in List) {
            it.findResult { inner ->
              //println "I: $inner -- " + inner.getClass()
              findJsonElement(type, (it in Map) ? inner.value : inner)
            }
          } else if (it in Map.Entry) {
            findJsonElement(type, it.value)
          }
        }
      }
    }
  }
}
