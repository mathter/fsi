package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AInsert extends WithAttributes {
  def insert(): String = {
    this.attribute(AName.attr)
  }
}

object AInsert {
  val attr = "insert"
}