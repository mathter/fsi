package biz.ostw.fsi.xml

import biz.ostw.fsi.ContainerPart

/**
  * Created by mathter on 28.07.17.
  */
class ImplicitWithAttributes(val part: ContainerPart) extends WithAttributes {
  override def attributes(): Array[AttributePart] = {
    this.part.getByType[AttributePart]
  }


  override def attributes(name: String): Array[AttributePart] = {
    Option(name)
      .map(name =>
        this.part.getByType[AttributePart]
          .filter(a => name.equals(a.name))).getOrElse(new Array[AttributePart](0))
  }

  override def attribute(name: String, value: String): AttributePart = {
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

  override def attribute(name: String): String = {

    Option(this.part.getByType[AttributePart].find(_.name.equals(name)).map(_.value).orNull).orNull
  }
}
