package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ALazy extends WithAttributes {
  def _lazy(): String = {
    this.attribute(AName.attr)
  }
}

object ALazy {
  val attr = "lazy"
}