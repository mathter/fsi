package biz.ostw.fsi.xml

import biz.ostw.fsi.Part

/**
  * Created by mathter on 29.07.17.
  */
class CDataPart extends Part {

  private var _start: Int = 0

  private var _text: CharSequence = null

  def this(text: String) = {
    this()
    this._text = text
  }

  override def start(): Int = {
    _start
  }

  override def stop(): Int = {
    this._start + this._text.length - 1
  }

  override def text(): String = {
    CDataPart.cdata_start + this._text + CDataPart.sdata_end
  }

  def text(text: String): Unit = {
    this._text = text
  }

  override def recalc(start: Int): Int = {
    this._start = start
    this._start + this.text().length
  }
}

object CDataPart{
  private val cdata_start = "<![CDATA["

  private val sdata_end = "]]>"
}
