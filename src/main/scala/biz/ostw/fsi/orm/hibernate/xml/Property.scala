package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.WrapperPart
import biz.ostw.fsi.xml.{ElementPart, WithAttributes, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class Property(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with ANode
    with AAccess
    with AColumn
    with AType
    with ALength
    with APrecision
    with AScale
    with ANotNull
    with AUnique
    with AInsert
    with AUpdate
    with ALazy
    with AGenerated {

}

object Property {
  val elem_property = "property"
}