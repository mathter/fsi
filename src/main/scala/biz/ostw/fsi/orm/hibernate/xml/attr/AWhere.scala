package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AWhere extends WithAttributes {
  def where(): String = {
    this.attribute(AWhere.attr)
  }
}

object AWhere {
  val attr = "where"
}