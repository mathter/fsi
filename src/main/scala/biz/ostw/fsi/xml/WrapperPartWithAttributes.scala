package biz.ostw.fsi.xml

import biz.ostw.fsi.{Part, WrapperPart}

/**
  * @author mathter (c) 2017.
  */
class WrapperPartWithAttributes[T <: Part with WithAttributes](part: T) extends WrapperPart(part) with WithAttributes {

  override def attributes() = this.part.attributes()

  override def attributes(name: String) = this.part.attributes(name)

  override def attribute(name: String, value: String) = this.part.attribute(name, value)

  override def attribute(name: String) = this.part.attribute(name)
}
