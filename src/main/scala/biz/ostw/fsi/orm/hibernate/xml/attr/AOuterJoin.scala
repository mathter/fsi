package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AOuterJoin extends WithAttributes {
  def outerJoin(): String = {
    this.attribute(AOuterJoin.attr)
  }
}

object AOuterJoin {
  val attr = "outer-join"
}