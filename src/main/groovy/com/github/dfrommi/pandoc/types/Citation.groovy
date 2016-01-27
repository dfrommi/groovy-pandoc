package com.github.dfrommi.pandoc.types

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc


/**
 * Citation, used by Cite.
 */
@Pandoc
class Citation implements PandocType {
	/**
	 * Citation Modes
	 */
	enum CitationMode {
		AuthorInText,
		SuppressAuthor,
		NormalCitation
	}


	/**
	 * The citation id
	 */
	@JsonValue(key="citationId")
	String id = ""

	/**
	 * The citaion prefix.
	 */
	@Child 	@JsonValue(key="citationPrefix")
	Inline[] prefix = []
	
	/**
	 * The citation suffix. 
	 */
	@Child 	@JsonValue(key="citationSuffix")
	Inline[] suffix = []

	/**
	 * The citation mode.
	 */
	@JsonValue(key="citationMode")
	CitationMode mode = CitationMode.NormalCitation

	/**
	 * The note number. 
	 */
	@JsonValue(key="citationNoteNum")
	int noteNum = 0

	/**
	 * The citation hash.
	 */
	@JsonValue(key="citationHash")
	int hash = 0
}
