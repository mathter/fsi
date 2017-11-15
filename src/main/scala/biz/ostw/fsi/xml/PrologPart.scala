package biz.ostw.fsi.xml

import biz.ostw.fsi
import biz.ostw.fsi.ContainerPart

/**
  * Created by mathter on 28.07.17.
  */
class PrologPart() extends ContainerPart with WithAttributes {

  def version(): String = {
    this.attribute(PrologPart.version)
  }

  def version(version: String): Unit = {
    this.attribute(PrologPart.version, version)
  }

  def encoding(): String = {
    this.attribute(PrologPart.encoding)
  }

  def encoding(encoding: String): Unit = {
    this.attribute(PrologPart.encoding, encoding)
  }

  override def text(): String = {
    "<?xml" + super.text + "?>"
  }

  override def attributes() = new ImplicitWithAttributes(this).attributes()

  override def attributes(name: String) = new ImplicitWithAttributes(this).attributes(name)

  override def attribute(name: String, value: String) = new ImplicitWithAttributes(this).attribute(name, value)

  override def attribute(name: String) = new ImplicitWithAttributes(this).attribute(name)
}

object PrologPart {
  private val version: String = "version"

  private val encoding: String = "encoding"
}