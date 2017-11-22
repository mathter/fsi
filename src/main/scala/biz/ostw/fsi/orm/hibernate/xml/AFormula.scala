package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AFormula extends WithAttributes {
  def formula(): String = {
    this.attribute(AColumn.attr)
  }
}

object AFormula {
  val attr = "formula"
}