package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AClass extends WithAttributes {
  def access(): String = {
    this.attribute(AName.attr)
  }
}

object AClass {
  val attr = "class"
}
