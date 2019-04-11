package biz.ostw.fsi.translator

import java.net.URI

import biz.ostw.fsi.Part

/**
  * Created by mathter on 05.07.17.
  */
class PartSource(val part: Part, val uri: URI) extends Source[Part] {

  def this(part: Part) {
    this(part, new URI("fsi:translator:part"))
  }
}
