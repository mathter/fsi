package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AInsert extends WithAttributes {
  def insert(): String = {
    this.attribute(AInsert.attr)
  }
}

object AInsert {
  val attr = "insert"
}