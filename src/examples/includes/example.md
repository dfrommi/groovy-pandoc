Here's an example use of the recursive Include.groovy filter. This file contains 1 include block that references 2 files. One of those files, firstinclude.md, itself contains an include block, and so on.

Include.groovy has unlimited recursive includes and will process Groovy templates when it finds them (Groovy templates must use the .gt prefix for this to work). To include comments, simply begin lines with '#'.

Inside Groovy templates, to output to stdout or stderr, use:

```
out << "foo out\n"
err << "foo err\n"
```

If you use System.out/err, the template engine will not output inline and this causes pandoc errors.


```include
# This file contains an include and will kick off a chain recursive includes
firstinclude.md
README
```
