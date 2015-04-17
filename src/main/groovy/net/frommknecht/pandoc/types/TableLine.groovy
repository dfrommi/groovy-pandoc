package net.frommknecht.pandoc.types;

import net.frommknecht.pandoc.types.annotation.Child
import net.frommknecht.pandoc.types.annotation.JsonValue
import net.frommknecht.pandoc.types.annotation.Pandoc

@Pandoc
public class TableLine implements PandocType {
	@Child @JsonValue
	Block[][] cells = [][]
}
