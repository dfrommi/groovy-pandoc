package com.github.dfrommi.pandoc.types;

import com.github.dfrommi.pandoc.types.annotation.Child
import com.github.dfrommi.pandoc.types.annotation.JsonValue
import com.github.dfrommi.pandoc.types.annotation.Pandoc

@Pandoc
public class TableLine implements PandocType {
  @Child
  @JsonValue
  Block[][] cells = [][]
}
