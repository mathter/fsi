package biz.ostw.fsi.lang.xml

import biz.ostw.fsi.{ContainerPart, Part}

/**
  * Created by mathter on 29.07.17.
  */
class ElementPart() extends ContainerPart with WithAttributes with Named {

  val _openTag: OpenTag = new OpenTag

  private val _closeTag: CloseTag = new CloseTag

  def this(name: String) = {
    this()
    this._openTag.name(name)
    this._openTag.isFlashed(true)
    this._closeTag.name(name)
  }

  def isFlashed(): Boolean = {
    this._openTag.isFlashed
  }

  def isFlashed(isFlashed: Boolean): Unit = {
    this._openTag.isFlashed(isFlashed)
  }

  override def getByType[T <: Part]()(implicit ev: ClassManifest[T]): Array[T] = {
    ev match {
      case _: OpenTag => Array.fill(1) {
        this._openTag
      }.asInstanceOf[Array[T]]

      case _: CloseTag => Array.fill(1) {
        this._closeTag
      }.asInstanceOf[Array[T]]

      case _ => super.getByType[T]
    }
  }

  override def text(): String = {
    this._openTag.text + (if (this.childs.isEmpty) "" else super.text + this._closeTag.text)
  }

  override def recalc(start: Int) = {
    this._closeTag.recalc(super.recalc(this._openTag.recalc(start)))
  }

  override def name(name: String): Unit = {
    this._openTag.name(name)
    this._closeTag.name(name)
  }

  override def name(): String = {
    this._openTag.name
  }

  override def start(): Int = {
    this._openTag.start
  }

  override def stop(): Int = {
    this._closeTag.stop
  }

  override def attributes() = {
    this._openTag.attributes()
  }

  override def attributes(name: String) = {
    new ImplicitWithAttributes(this).attributes(name)
  }

  override def attribute(name: String, value: String) = {
    this._openTag.attribute(name, value)
  }

  override def attribute(name: String) = {
    this._openTag.attribute(name)
  }

  override def add(part: Part) = {
    super.add(part)
    this._openTag.isFlashed(false)
    this
  }

  override def add(afterPart: Part, part: Part) = {
    super.add(afterPart, part)
    this._openTag.isFlashed(false)
    this
  }

  override def addBefor(beforPart: Part, part: Part) = {
    super.addBefor(beforPart, part)
    this._openTag.isFlashed(false)
    this
  }

  override def remove(part: Part) = {
    super.remove(part)
    if (this.childs.isEmpty) {
      this._openTag.isFlashed(false)
    }
    this
  }
}
