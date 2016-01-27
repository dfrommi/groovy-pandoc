package com.github.dfrommi.pandoc.types

import static org.junit.Assert.*

import com.github.dfrommi.pandoc.Pandoc
import com.github.dfrommi.pandoc.convert.PandocConverter;

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter


@RunWith(Parameterized)
abstract class TypeTestBase {
	@Parameter
	public md
	@Parameter(value = 1)
	public obj

	PandocConverter converter = Pandoc.factory.converter
	
	protected getJsonElement() {
		def document = converter.mdToJson(md)
		findJsonElement(getJsonType(obj), document)
	}
	
	protected getJsonType(object) {
		object.getClass().simpleName as String
	}
	
	protected findJsonElement(type, node) {
		if(node in Map && node.t == type) {
			return node
		} else if(node in List || node in Map) { 
			node.findResult { 
				//println "X " + it + " -- " + it.getClass()
				if(it in Map && it.t == type) {
					it
				} else {
					if(it in Map || it in List) {
						it.findResult { inner ->
							//println "I: $inner -- " + inner.getClass()
							findJsonElement(type, (it in Map) ? inner.value : inner)
						}
					} else if( it in Map.Entry) {
						findJsonElement(type, it.value)
					}
				}
			}
		}
	}

	@Test
	void testInitFromJsonValue() {
		def json = getJsonElement()
		def newObject = converter.jsonToElement(json)
		assertEquals(obj, newObject)
	}

	@Test
	void testGetJsonValue() {
		def jsonResult = converter.elementToJson(obj)
		def expectedJson = getJsonElement()
		assertEquals(expectedJson, jsonResult)
	}
}
