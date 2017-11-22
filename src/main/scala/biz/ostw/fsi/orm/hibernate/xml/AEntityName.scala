package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AEntityName extends WithAttributes {
  def entityName(): String = {
    this.attribute(AName.attr)
  }
}

object AEntityName {
  val attr = "entity-name"
}