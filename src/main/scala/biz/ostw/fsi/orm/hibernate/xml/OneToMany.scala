package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.orm.hibernate.xml.attr.{AClass, AEmbedXml, AEntityName, ANode}
import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

class OneToMany(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AClass
    with ANode
    with AEmbedXml
    with AEntityName {

}

object OneToMany {
  val elem = "one-to-many"
}
