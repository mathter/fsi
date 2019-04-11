package biz.ostw.fsi.lang.xml

import biz.ostw.fsi
import biz.ostw.fsi.ContainerPart

/**
  * Created by mathter on 28.07.17.
  */
class PrologPart() extends ContainerPart {

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
}

object PrologPart {
  private val version: String = "version"

  private val encoding: String = "encoding"

  implicit def OpenTag2WithAttributes(part: PrologPart): ImplicitWithAttributes = new ImplicitWithAttributes(part)
}