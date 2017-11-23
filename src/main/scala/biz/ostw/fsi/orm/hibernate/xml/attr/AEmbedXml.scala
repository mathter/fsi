package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait AEmbedXml extends WithAttributes {
  def embedXml(): String = {
    this.attribute(AEmbedXml.attr)
  }
}

object AEmbedXml {
  val attr = "embed-xml"
}