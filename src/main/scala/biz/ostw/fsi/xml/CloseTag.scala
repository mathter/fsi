package biz.ostw.fsi.xml

import biz.ostw.fsi.Part

/**
  * Created by mathter on 29.07.17.
  */
protected class CloseTag() extends Part with Named {

  private var _start: Int = 0

  private var _name: String = ""

  override def name(): String = {
    this._name
  }

  override def name(name: String): Unit = {
    this._name = name
  }

  override def start: Int = {
    this._start
  }

  override def stop: Int = {
    this._start + this.text.length
  }


  override def text(): String = {
    CloseTag.OPEN + this.name + CloseTag.CLOSE
  }

  override def recalc(start: Int): Int = {
    this._start = start
    this.start + 2 + this.name.length + 1
  }
}

object CloseTag {

  private val OPEN = "</"

  private val CLOSE = ">"
}
