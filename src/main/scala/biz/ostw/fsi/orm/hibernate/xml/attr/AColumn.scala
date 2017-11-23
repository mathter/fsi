package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AColumn extends WithAttributes {
  def column(): String = {
    this.attribute(AColumn.attr)
  }
}

object AColumn {
  val attr = "column"
}