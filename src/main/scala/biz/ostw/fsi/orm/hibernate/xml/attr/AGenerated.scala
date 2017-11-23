package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AGenerated extends WithAttributes {
  def generated(): String = {
    this.attribute(AGenerated.attr)
  }
}

object AGenerated {
  val attr = "generated"
}