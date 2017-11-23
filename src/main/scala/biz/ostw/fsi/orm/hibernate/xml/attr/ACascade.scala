package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ACascade extends WithAttributes {
  def cascade(): String = {
    this.attribute(ACascade.attr)
  }
}

object ACascade {
  val attr = "cascade"
}