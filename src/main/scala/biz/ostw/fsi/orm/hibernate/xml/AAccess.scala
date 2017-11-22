package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AAccess extends WithAttributes {
  def access(): String = {
    this.attribute(AName.attr)
  }
}

object AAccess {
  val attr = "access"
}