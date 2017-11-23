package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.orm.hibernate.xml.attr.{AClass, ALazy, AName, ANode}
import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class Component(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with ALazy
    with ANode
    with AClass {
}

object Component {
  val elem_component = "component"
}

