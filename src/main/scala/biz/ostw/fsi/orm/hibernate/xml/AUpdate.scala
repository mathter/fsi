package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AUpdate extends WithAttributes {
  def update(): String = {
    this.attribute(AName.attr)
  }
}

object AUpdate {
  val attr = "update"
}