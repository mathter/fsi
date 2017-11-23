package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AAccess extends WithAttributes {
  def access(): String = {
    this.attribute(AAccess.attr)
  }
}

object AAccess {
  val attr = "access"
}