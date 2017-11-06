package biz.ostw.fsi.xml

import biz.ostw.fsi.Part


/**
  * * Created by mathter on 28.07.17.
  */
class AttributePart extends Part {

  private var _namePart: TerminalPart = null

  private var _valuePart: TerminalPart = null

  private var _brace = '\"'

  def this(name: String, value: String, brace: Char) = {
    this()

    if (name == null) {
      throw new NullPointerException("'name' of the attribute can't be null!")
    }

    this._namePart = PartFactory.terminal(name)
    this._valuePart = PartFactory.terminal(value)
  }

  override def start: Int = this._namePart.start

  override def stop: Int = this._valuePart.stop


  override def text(): String = {
    this._namePart.text + "=" + this._brace + this._valuePart.text + this._brace
  }

  override def recalc(start: Int): Int = {
    this._valuePart.recalc(this._namePart.recalc(start) + 2) + 1
  }

  override def toString: String = this.text.toString

  def name(): String = {
    this._namePart.text.toString
  }

  def value(): String = {
    this._valuePart.text.toString
  }

  def name(name: String): Unit = {
    this._namePart.text(name)
  }

  def value(value: String): Unit = {
    this._valuePart.text(value)
  }

  def brace(): Char = this._brace

  def brace(brace: Char): Unit = this._brace = brace
}
