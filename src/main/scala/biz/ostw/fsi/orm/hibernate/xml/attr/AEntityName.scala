package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AEntityName extends WithAttributes {
  def entityName(): String = {
    this.attribute(AEntityName.attr)
  }
}

object AEntityName {
  val attr = "entity-name"
}