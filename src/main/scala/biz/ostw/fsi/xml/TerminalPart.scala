package biz.ostw.fsi.xml

import biz.ostw.fsi.Part

/**
  * Created by mathter on 28.07.17.
  */
class TerminalPart() extends Part {

  private var _start: Int = 0

  private var _text: String = null

  def this(_text: String) = {
    this()
    this._text = _text
  }

  override def start(): Int = {
    _start
  }

  override def stop(): Int = {
    this._start + this._text.length - 1
  }

  override def text(): String = {
    this._text
  }

  def text(text: String): Unit = {
    this._text = text
  }

  override def recalc(start: Int): Int = {
    this._start = start
    this._start + this._text.length
  }
}