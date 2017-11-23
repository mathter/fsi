package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AOrderBy extends WithAttributes {
  def orderBy(): String = {
    this.attribute(AOrderBy.attr)
  }
}

object AOrderBy {
  val attr = "order-by"
}