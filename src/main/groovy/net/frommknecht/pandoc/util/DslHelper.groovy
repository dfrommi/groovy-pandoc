package net.frommknecht.pandoc.util

import groovy.lang.Closure;
import net.frommknecht.pandoc.types.PandocType;

class DslHelper {

	static doClosure(target, Closure closure) {
		def cl = closure.clone()
		cl.delegate = target
		cl.resolveStrategy = Closure.DELEGATE_ONLY
		cl()
	}
}
