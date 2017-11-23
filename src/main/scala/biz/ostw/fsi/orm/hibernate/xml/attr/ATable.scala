package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ATable extends WithAttributes {
  def table(): String = {
    this.attribute(ATable.attr)
  }
}

object ATable {
  val attr = "table"
}