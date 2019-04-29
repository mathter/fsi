package biz.ostw.fsi.lang.java.part.common

import biz.ostw.fsi.Part

class TextPart(private var _start: Int, private var _text: String) extends Part {


  /**
    * Return index of first symbol of the part.
    *
    * @return index of first symbol of the part.
    */
  override def start(): Int = this._start

  /**
    * Method return text representation of the part.
    * Always return not `null`.
    *
    * @return text representation of the part.
    */
  override def text(): String = this._text

  /**
    * Recalculate index of the first lement of the part using `start` parameter of the method.
    *
    * @param start new start index.
    * @return index of last symbol of the part.
    */
  override def recalc(start: Int): Int = {
    this._start = start
    super.recalc(start)
  }
}