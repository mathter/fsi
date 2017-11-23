package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.orm.hibernate.xml.attr._
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
    with AEntityName
    with ADiscriminatorValue {

  def id(): Id = {
    this.elementPart
      .getByType[ElementPart]
      .find(e => Id.elem_id.equals(e.name)).map(new Id(_)).orNull
  }

  def version(): Version = {
    this.elementPart
      .getByType[ElementPart]
      .find(e => Version.elem_version.equals(e.name)).map(new Version(_)).orNull
  }

  def manyToOne(): Array[ManyToOne] = {
    this.elementPart
      .getByType[ElementPart]
      .filter(e => ManyToOne.elem_many_to_one.equals(e.name))
      .map(new ManyToOne(_))
  }

  def properties(): Array[Property] = {
    this.elementPart
      .getByType[ElementPart]
      .filter(e => Property.elem_property.equals(e.name))
      .map(new Property(_))
  }
}

object Class {
  val elem_class = "class"
}