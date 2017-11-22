package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ALength extends WithAttributes {
  def length(): String = {
    this.attribute(AName.attr)
  }
}

object ALength {
  val attr = "length"
}