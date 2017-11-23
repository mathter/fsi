package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait APrecision extends WithAttributes {
  def precision(): String = {
    this.attribute(APrecision.attr)
  }
}

object APrecision {
  val attr = "precision"
}