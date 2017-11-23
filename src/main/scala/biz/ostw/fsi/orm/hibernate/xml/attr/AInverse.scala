package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AInverse extends WithAttributes {
  def inverse(): String = {
    this.attribute(AInverse.attr)
  }
}

object AInverse {
  val attr = "inverse"
}