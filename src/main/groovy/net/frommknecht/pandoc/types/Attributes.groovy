package net.frommknecht.pandoc.types

import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc


/**
 * Generic attributes used by several Pandoc elements like Header.
 */
@Pandoc
class Attributes implements PandocType {
	/**
	 * The identfier.
	 */
	@JsonValue(index=1)
	def identifier = ""
	
	/**
	 * List of classes
	 */
	@JsonValue(index=2)
	def classes = []
	
	/**
	 * Attribute map
	 */
	@JsonValue(index=3, 
		fromJson={r -> r.collectEntries{[(it[0]): it[1]]}},
		toJson={it.collect{k,v->[k,v]}}
	)
	def properties = [:]
}
