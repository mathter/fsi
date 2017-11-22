package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ANode extends WithAttributes {
  def node(): String = {
    this.attribute(AName.attr)
  }
}

object ANode {
  val attr = "node"
}