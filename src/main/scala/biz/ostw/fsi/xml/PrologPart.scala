package biz.ostw.fsi.xml

import biz.ostw.fsi
import biz.ostw.fsi.ContainerPart

/**
  * Created by mathter on 28.07.17.
  */
class PrologPart() extends ContainerPart with WithAttributes {

  private val _version: String = "version"

  private val _encoding: String = "encoding"

  def version(): String = {
    this.attribute(_version)
  }

  def version(version: String): Unit = {
    this.attribute(_version, version)
  }

  def encoding(): String = {
    this.attribute(_encoding)
  }

  def encoding(encoding: String): Unit = {
    this.attribute(_encoding, encoding)
  }

  override def text(): String = {
    "<?xml" + super.text + "?>"
  }

  override def attributes() = new ImplicitWithAttributes(this).attributes()

  override def attribute(name: String, value: String) = new ImplicitWithAttributes(this).attribute(name, value)

  override def attribute(name: String) = new ImplicitWithAttributes(this).attribute(name)
}