package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class Id(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart)
    with AName
    with ANode
    with AAccess
    with AColumn
    with AType
    with ALength {

  def generator(): String = {
    this.elementPart
      .getByType[ElementPart]
      .find(e => Id.elem_generator.equals(e.name))
      .map(_.attribute(Id.attr_generator_class)).orNull
  }
}

object Id {
  val elem_id = "id"

  val elem_generator = "generator"

  val attr_generator_class = "class"
}
