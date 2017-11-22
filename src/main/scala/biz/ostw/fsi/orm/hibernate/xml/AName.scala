package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.Part
import biz.ostw.fsi.xml.WithAttributes
/**
  * @author mathter (c) 2017.
  */
trait AName extends WithAttributes {
  def name(): String = {
    this.attribute(AName.attr)
  }
}

object AName {
  val attr = "name"
}