package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait APropertyRef  extends WithAttributes {
  def propertyRef(): String = {
    this.attribute(APropertyRef.attr)
  }
}

object APropertyRef {
  val attr = "property-ref"
}