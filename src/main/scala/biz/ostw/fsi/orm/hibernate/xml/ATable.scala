package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ATable extends WithAttributes {
  def table(): String = {
    this.attribute(AName.attr)
  }
}

object ATable {
  val attr = "table"
}