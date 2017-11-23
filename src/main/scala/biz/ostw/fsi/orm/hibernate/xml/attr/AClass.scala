package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AClass extends WithAttributes {
  def _class(): String = {
    this.attribute(AClass.attr)
  }
}

object AClass {
  val attr = "class"
}
