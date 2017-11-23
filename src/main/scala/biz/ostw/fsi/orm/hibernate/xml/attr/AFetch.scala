package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AFetch extends WithAttributes {
  def fetch(): String = {
    this.attribute(AFetch.attr)
  }
}

object AFetch {
  val attr = "fetch"
}