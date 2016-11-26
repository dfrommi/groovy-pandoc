package com.github.dfrommi.pandoc.util

class DslHelper {

  static doClosure(target, Closure closure) {
    def cl = closure.clone()
    cl.delegate = target
    cl.resolveStrategy = Closure.DELEGATE_ONLY
    cl()
  }
}
