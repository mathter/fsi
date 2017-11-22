package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class Class(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with ALazy
    with ATable
    with ANode
    with AAbstract
    with AWhere
    with AEntityName {

}

object Class {
  val elem_class = "class"
}