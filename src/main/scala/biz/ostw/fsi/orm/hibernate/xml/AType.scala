package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AType extends WithAttributes {
  def _type(): String = {
    this.attribute(AName.attr)
  }
}

object AType {
  val attr = "type"
}