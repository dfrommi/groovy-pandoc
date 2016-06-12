package com.github.dfrommi.pandoc

import com.github.dfrommi.pandoc.convert.PandocConverter
import spock.lang.Ignore
import spock.lang.Specification

class PandocConverterSpec extends Specification {

	@Ignore
  def "verify missing conversion methods are added by methodMissing"() {
    given:
		PandocConverter conv = new PandocConverter()

    when:
		def result = conv.mdToJson("Hello world")

    then:
    println result

    when:
		def obj = conv.mdToObject("Hello *world*")
    then:
		println obj
		println conv.objectToMd(obj)
		conv.objectToSomething("hallo")
	}
}
