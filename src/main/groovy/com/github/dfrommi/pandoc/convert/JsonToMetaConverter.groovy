package com.github.dfrommi.pandoc.convert

import com.github.dfrommi.pandoc.types.Block
import com.github.dfrommi.pandoc.types.Inline
import com.github.dfrommi.pandoc.types.Meta

/**
 * Json to Metadata converter
 */
class JsonToMetaConverter implements JsonTypeConverter{
	/**
	 * The converter
	 */
	JsonToPandocTypeConverter converter
	
	/* (non-Javadoc)
	 * @see JsonTypeConverter#fromJson(java.lang.Object)
	 */
	def fromJson(json) {
		Meta meta = new Meta()
		initFromJson(meta, json)
		meta
	}
	
	/* (non-Javadoc)
	 * @see JsonTypeConverter#toJson(java.lang.Object)
	 */
	def toJson(meta) {
		[unMeta: metadataToJson(meta)]
	}
	
	/**
	 * Initialize object from json.
	 * 
	 * @param meta Metadata object
	 * @param json Json structure
	 * @return the initialized metadata object 
	 */
	private initFromJson(Meta meta, json) {
		meta.metadata = [:]
		json.unMeta.each {k, v ->
			meta.metadata[k] = jsonToInt(v)
		}
		meta
	}

	/**
	 * Convertion of metadata object to json structure.
	 * 
	 * @param meta the Metadata object
	 * @return jsonData
	 */
	private metadataToJson(Meta meta) {
		def jsonData = [:]
		meta.metadata.each { k, v ->
			jsonData[k] = intToJson(v)
		}
		jsonData
	}
	
	/**
	 * Converts internal data representation to external json representation.
	 * 
	 * @param value the internal value
	 * @return external value
	 */
	private intToJson(value) {
		switch(value) {
			case String:
				return [c:value, t:"MetaString"]
			case Boolean:
				return [c:value, t:"MetaBool"]
			case {v -> v in List && v.every{ it in Inline }}:
				return [c:value.collect{converter.toJson(it)}, t:"MetaInlines"]
			case {v -> v in List && v.every{ it in Block }}:
				return [c:value.collect{converter.toJson(it)}, t:"MetaBlocks"]
			case List:
				return [c:value.collect{intToJson(it)}, t:"MetaList"]
			case Map:
				return [c:value.collectEntries{k, v -> [(k): intToJson(v)]}, t:"MetaMap"]
		}
		
	}
	
	/**
	 * Converts external to internal representation.
	 * 
	 * @param jsonValue the external value
	 * @return the internal value
	 */
	private jsonToInt(jsonValue) {
		switch(jsonValue.t) {
			case 'MetaString':
				return jsonValue.c as String
			case 'MetaBool':
				return jsonValue.c as boolean
			case 'MetaList':
				return jsonValue.c.collect{ jsonToInt(it) }
			case 'MetaMap':
				return jsonValue.c.collectEntries{k, v -> [(k), jsonToInt(v)]}
			case 'MetaInlines':
				return jsonValue.c.collect{ converter.fromJson(it) }
			case 'MetaBlocks':
				return jsonValue.c.collect{ converter.fromJson(it) }
		}
		
	}
}
