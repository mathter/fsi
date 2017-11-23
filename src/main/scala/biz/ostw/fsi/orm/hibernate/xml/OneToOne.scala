package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.orm.hibernate.xml.attr._
import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class OneToOne(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with AFormula
    with AAccess
    with AClass
    with AEntityName
    with ACascade
    with AOuterJoin
    with AFetch
    with AForeignKey
    with APropertyRef
    with ALazy
    with ANode
    with AEmbedXml {
}

object OneToOne {
  val elem_one_to_one = "one-to-one"
}