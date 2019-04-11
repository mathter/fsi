package biz.ostw.fsi.lang.xml

import biz.ostw.fsi.Part

/**
  * Created by mathter on 28.07.17.
  */
class TerminalPart() extends Part {

  private var _start: Int = 0

  private var _text: String = null

  def this(_text: String) = {
    this()

    if (_text == null) {
      throw new NullPointerException("value of the therminal part can't be null!")
    }

    this._text = _text
  }

  override def start(): Int = {
    this._start
  }

  override def text(): String = {
    this._text
  }

  def text(text: String): Unit = {
    this._text = text
  }
}