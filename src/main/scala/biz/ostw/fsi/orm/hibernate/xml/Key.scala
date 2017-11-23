package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.orm.hibernate.xml.attr._
import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class Key(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AColumn
    with APropertyRef
    with ANotNull
    with AUpdate
    with AUnique {
}

object Key {
  val elem = "key"
}
