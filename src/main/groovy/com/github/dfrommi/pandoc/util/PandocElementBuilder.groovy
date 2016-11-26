package com.github.dfrommi.pandoc.util

import com.github.dfrommi.pandoc.types.*

class PandocElementBuilder {
  def elements = []

  private PandocType[] getPandocElements(Closure closure) {
    def h = new PandocElementBuilder()
    DslHelper.doClosure(h, closure)
    h.elements
  }

  private pushAndGet(obj) {
    elements << obj
    obj
  }

  def str(String text) {
    pushAndGet(new Str(text))
  }

  def emph(Closure closure) {
    pushAndGet(new Emph(inlines: getPandocElements(closure)))
  }

  def header(level, closure) {
    pushAndGet(new Header(level: level, inlines: getPandocElements(closure)))
  }

  def para(closure) {
    pushAndGet(new Para(inlines: getPandocElements(closure)))
  }

}
