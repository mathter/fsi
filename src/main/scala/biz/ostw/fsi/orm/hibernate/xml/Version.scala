package biz.ostw.fsi.orm.hibernate.xml


import biz.ostw.fsi.orm.hibernate.xml.attr._
import biz.ostw.fsi.xml.{ElementPart, WithAttributes, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class Version(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with ANode
    with AAccess
    with AColumn
    with AType
    with ALength
    with AGenerated
    with AInsert {
}

object Version {
  val elem_version = "version"
}
