package biz.ostw.fsi.lang.xml

import biz.ostw.fsi.Part

/**
  * Created by mathter on 29.07.17.
  */
class CommentPart extends Part {

  private var _start: Int = 0

  private var _text: CharSequence = null

  def this(text: String) = {
    this()
    this._text = text
  }

  override def start(): Int = {
    _start
  }

  override def text: String = {
    CommentPart.comment_start + this._text + CommentPart.comment_end
  }

  def text(text: String): Unit = {
    this._text = text
  }

  override def recalc(start: Int): Int = {
    this._start = start
    this._start + this.text.length
  }
}

object CommentPart {
  private val comment_start = "<!--"

  private val comment_end = "-->"
}
