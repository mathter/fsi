package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ANotNull extends WithAttributes {
  def notNull(): String = {
    this.attribute(ANotNull.attr)
  }
}

object ANotNull {
  val attr = "not-null"
}