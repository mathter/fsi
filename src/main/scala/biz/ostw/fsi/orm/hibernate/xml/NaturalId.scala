package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.{ElementPart, WrapperPartWithAttributes}

/**
  * @author mathter (c) 2017.
  */
class NaturalId(elementPart: ElementPart)
  extends WrapperPartWithAttributes(elementPart) {

  def mutable(): String = {
    this.attribute(NaturalId.attr_mutable)
  }

  def properties(): Array[Property] = {
    this.elementPart
      .getByType[ElementPart]
      .filter(e => Property.elem_property.equals(e.name))
      .map(new Property(_))
  }
}

object NaturalId {
  val elem_natural_id = "natural-id"

  val attr_mutable = "mutable"
}
