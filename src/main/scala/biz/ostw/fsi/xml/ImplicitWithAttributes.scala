package biz.ostw.fsi.xml

import biz.ostw.fsi.ContainerPart

/**
  * Created by mathter on 28.07.17.
  */
class ImplicitWithAttributes(val part: ContainerPart) extends WithAttributes {
  def attributes(): Array[AttributePart] = {
    this.part.getByType[AttributePart]
  }

  def attribute(name: String, value: String): AttributePart = {
    var attributePart: AttributePart = this.part.getByType[AttributePart].find(_.name.equals(name)).getOrElse(null)

    if (value != null) {
      if (attributePart == null) {
        attributePart = PartFactory.attribute(name, value)
        this.part.add(PartFactory.terminal(" "))
        this.part.add(attributePart)
      }
      else {
        attributePart.name(name)
        attributePart.value(value)
      }
    } else {
      if (attributePart != null) {
        this.part.remove(attributePart)
      }
    }

    attributePart
  }

  def attribute(name: String): String = {

    Option(this.part.getByType[AttributePart].filter(_.name.equals(name)).headOption.getOrElse(null))
      .map(_.value).get
  }
}
