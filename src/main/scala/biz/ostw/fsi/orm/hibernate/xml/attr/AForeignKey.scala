package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AForeignKey extends WithAttributes {
  def foreignKey(): String = {
    this.attribute(AForeignKey.attr)
  }
}

object AForeignKey {
  val attr = "foreign-key"
}