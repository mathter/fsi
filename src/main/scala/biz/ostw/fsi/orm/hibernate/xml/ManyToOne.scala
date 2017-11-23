package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.orm.hibernate.xml.attr._
import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class ManyToOne(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with AAccess
    with AClass
    with AEntityName
    with AColumn
    with ANotNull
    with AUnique
    with ACascade
    with AOuterJoin
    with AFetch
    with AUpdate
    with AInsert
    with AForeignKey
    with APropertyRef
    with AFormula
    with ALazy
    with ANode
    with AEmbedXml {
}

object ManyToOne {
  val elem_many_to_one = "many-to-one"
}