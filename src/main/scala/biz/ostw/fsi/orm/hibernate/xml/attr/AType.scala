package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AType extends WithAttributes {
  def _type(): String = {
    this.attribute(AType.attr)
  }
}

object AType {
  val attr = "type"
}