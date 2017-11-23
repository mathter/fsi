package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ANode extends WithAttributes {
  def node(): String = {
    this.attribute(ANode.attr)
  }
}

object ANode {
  val attr = "node"
}