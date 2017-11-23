package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ACollectionType extends WithAttributes {
  def collectionType(): String = {
    this.attribute(ACollectionType.attr)
  }
}

object ACollectionType {
  val attr = "collection-type"
}