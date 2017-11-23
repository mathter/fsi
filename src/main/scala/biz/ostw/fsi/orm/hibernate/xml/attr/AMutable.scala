package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AMutable extends WithAttributes {
  def mutable(): String = {
    this.attribute(AMutable.attr)
  }
}

object AMutable {
  val attr = "mutable"
}