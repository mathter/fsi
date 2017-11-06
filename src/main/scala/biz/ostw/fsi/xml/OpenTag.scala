package biz.ostw.fsi.xml

import biz.ostw.fsi.ContainerPart

/**
  * Created by mathter on 29.07.17.
  */
protected class OpenTag extends ContainerPart with Named {

  private var _start: Int = 0

  private var _name: String = ""

  protected var _isFlashed: Boolean = false

  override def start: Int = {
    this._start
  }

  override def stop: Int = {
    this._start + this.text.length
  }

  override def name(): String = {
    this._name
  }

  override def name(name: String): Unit = {
    this._name = name
  }

  def isFlashed(): Boolean = {
    this._isFlashed
  }

  def isFlashed(isFlashed: Boolean): Unit = {
    this._isFlashed = isFlashed
  }

  override def text(): String = {

    OpenTag.OPEN + this.name + super.text + (if (this.isFlashed) OpenTag.SLASH else OpenTag.CLOSE)
  }

  override def recalc(start: Int): Int = {
    this._start = start

    super.recalc(this._start + 1 + this.name.length) + (if (this.isFlashed) 2 else 1)
  }
}

object OpenTag {

  private val OPEN = "<"

  private val CLOSE = ">"

  private val SLASH = "/>"

  implicit def OpenTag2WithAttributes(openTag: OpenTag): ImplicitWithAttributes = new ImplicitWithAttributes(openTag)
}
