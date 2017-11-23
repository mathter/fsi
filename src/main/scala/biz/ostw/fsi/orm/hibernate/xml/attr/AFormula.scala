package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AFormula extends WithAttributes {
  def formula(): String = {
    this.attribute(AFormula.attr)
  }
}

object AFormula {
  val attr = "formula"
}