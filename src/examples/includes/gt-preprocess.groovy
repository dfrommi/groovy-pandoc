#!/usr/bin/env groovy

import groovy.text.SimpleTemplateEngine

//
// This script passes a groovy template through the
// StreamingTemplateEngine whether presented as input
// on stdin or as a file
//

def engine = new groovy.text.SimpleTemplateEngine()
def tmplt

if (args.length == 1) {
	tmplt = engine.createTemplate(new File(args[0])).make()
	//System.err << "Processing Groovy template ${args[0]}\n"
}
else {
	tmplt = engine.createTemplate(new BufferedReader(new InputStreamReader(System.in))).make()
	//System.err << "Processing Groovy template on STDIN\n"
}


println tmplt.toString()

