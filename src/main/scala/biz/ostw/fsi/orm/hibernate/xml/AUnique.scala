package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AUnique extends WithAttributes {
  def unique(): String = {
    this.attribute(AName.attr)
  }
}

object AUnique {
  val attr = "unique"
}