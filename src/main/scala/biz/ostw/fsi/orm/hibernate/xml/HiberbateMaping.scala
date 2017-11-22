package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.DocumentPart
import biz.ostw.fsi.{ContainerPart, Part, WrapperPart}

/**
  * @author mathter (c) 2017.
  */
class HiberbateMaping(documentPart: DocumentPart) extends WrapperPart(documentPart) {
  def pack(): String = {
    this.documentPart.root.map(_.attribute(HiberbateMaping.attr_package)).orNull
  }

  def defaultLazy(): String = {
    this.documentPart.root.map(_.attribute(HiberbateMaping.attr_package)).orNull
  }
}

object HiberbateMaping {
  val elem_hibernate_mapping = "hibernate-mapping"

  val attr_package = "package"

  val attr_default_lazy = "default-lazy"
}
