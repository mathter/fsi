package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AAbstract extends WithAttributes {
  def _abstract(): String = {
    this.attribute(AAbstract.attr)
  }
}

object AAbstract {
  val attr = "abstract"
}