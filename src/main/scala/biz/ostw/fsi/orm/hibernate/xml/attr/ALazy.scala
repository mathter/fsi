package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ALazy extends WithAttributes {
  def _lazy(): String = {
    this.attribute(ALazy.attr)
  }
}

object ALazy {
  val attr = "lazy"
}