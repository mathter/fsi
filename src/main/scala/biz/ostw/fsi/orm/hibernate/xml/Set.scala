package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.orm.hibernate.xml.attr._
import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class Set(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with AAccess
    with ATable
    with ALazy
    with AInverse
    with AMutable
    with ACascade
    with AOrderBy
    with AWhere
    with AOuterJoin
    with AFetch
    with ACollectionType
    with ANode
    with AEmbedXml {
}

object Set {
  val elem = "set"
}