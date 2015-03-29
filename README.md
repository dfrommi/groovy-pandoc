# Groovy wrapper for Pandoc JSON filters

[Pandoc](http://johnmacfarlane.net/pandoc/index.html) is the swiss army knife of document conversion, especially when Markdown is somewhere in the game. It supports filters transforming the Pandoc AST after the input is parsed and before the output is written.

This library helps writing filters in Groovy programming language. It has been heavily inspired by [Pandoc filter for Python](https://github.com/jgm/pandocfilters).

## How to use filters
Pandoc filters perform transformation of Pandoc AST in JSON format. They are executed like this:
```bash
pandoc -f SOURCEFORMAT -t TARGETFORMAT --filter ./Behead.groovy
```

Filter script `Behead.groovy` has to be executable. For more details on Pandoc filters see [Pandoc scripting guide](http://johnmacfarlane.net/pandoc/scripting.html)

## Development guide
The GroovyPandoc library is not working on plain JSON format, but instead converts it to object tree. Each Pandoc element has a corresponding Groovy class. See package `net.frommknecht.pandoc.types` for details.

The filter entry point is the static method `toJSONFilter` of class `net.frommknecht.pandoc.Pandoc`. It walks through Pandoc AST using breadth first search and calls the provided closure for each element. Typically, the filter closure first checks if the element is of interest and then transforms it.

### AST transformation
The modification done on Pandoc AST depends on closure's return value:

`null`
  ~ No modification of AST
  
Empty list `[]`
  ~ Remove current element from document

Single element
  ~ Replace current element by returned element

List of elements
  ~ Replace current element by all elements of list
  
### Closure types  
Two types of closures are supported. 

If transformation closure expects one parameter, then the Pandoc element is passed. 

If 2 parameters are expected, then first one is still the Pandoc element and second is metadata object (`net.frommknecht.pandoc.types.Meta`), which is a representation of YAML metadata at beginning of document.
 
### Example
The source code contains some example filters in directory `src/examples`. Let's have a closer look at the Behead example, which transforms all headers with level greater or equal to 2 to a paragraph with emphasized text.

```groovy
#!/usr/bin/env groovy -cp GroovyPandoc.jar

import static net.frommknecht.pandoc.Pandoc.*
import net.frommknecht.pandoc.types.*

toJSONFilter { 
	if(it in Header && it.level >= 2) {
		new Para(new Emph(text: it.text))
	}
}
```

The script requires shebang at first line. It tells the shell use Groovy interpreter for execution. GroovyPandoc.jar is required in classpath, so we add it to the Groovy call. The JAR file is searched relative from the directory where pandoc is executed, not relative to the directory of filter script.

`toJSONFilter` is the starting point of the filter. We are interested only in `Header` elements with a level greater or equal to 2. That is verified in if clause. For all elements that are not matching the condition, `null` is automatically returned and therefore no modification is performed.

If the condition is met, then we return a new `Para` object with `Emph` as only child. The text is copied from `Header` element. In that case, only a single object is returned and therefore the `Header` object is replaced by `Para` object Pandoc AST.

There is a second variant of `toJSONFilter`, expecting a class as first parameter. The Closure is then only called if the element is of given type. With the modifired `toJSONFilter` call, above example would look like this:

```groovy
#!/usr/bin/env groovy -cp GroovyPandoc.jar

import static net.frommknecht.pandoc.Pandoc.*
import net.frommknecht.pandoc.types.*

toJSONFilter(Header) { Header h -> 
	if(h.level >= 2) {
		new Para(new Emph(text: h.text))
	}
}
```
