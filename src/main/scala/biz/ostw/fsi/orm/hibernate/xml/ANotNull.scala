package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ANotNull extends WithAttributes {
  def notNull(): String = {
    this.attribute(AName.attr)
  }
}

object ANotNull {
  val attr = "not-null"
}