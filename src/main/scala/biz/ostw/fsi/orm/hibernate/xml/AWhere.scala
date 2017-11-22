package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AWhere extends WithAttributes {
  def where(): String = {
    this.attribute(AName.attr)
  }
}

object AWhere {
  val attr = "where"
}