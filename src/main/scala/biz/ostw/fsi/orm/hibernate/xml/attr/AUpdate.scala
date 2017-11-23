package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AUpdate extends WithAttributes {
  def update(): String = {
    this.attribute(AUpdate.attr)
  }
}

object AUpdate {
  val attr = "update"
}