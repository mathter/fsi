package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AScale extends WithAttributes {
  def scale(): String = {
    this.attribute(AName.attr)
  }
}

object AScale {
  val attr = "scale"
}