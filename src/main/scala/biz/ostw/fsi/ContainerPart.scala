package biz.ostw.fsi

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
  * Created by mathter on 23.06.17.
  */
class ContainerPart extends Part {

  private val _childs = new ArrayBuffer[Part](0)

  override def start() = {
    this._childs.headOption.map(_.start).getOrElse(0)
  }

  override def stop() = {
    Option(this._childs).map(_.lastOption.map(_.stop).get).getOrElse(0)
  }

  def content(): Array[Part] = {
    this.childs
  }

  def contentAsText(): String = {
    this._childs.foldLeft("")((s, e) => s.concat(e.text))
  }

  def add(part: Part): this.type = {
    this._childs += part
    this
  }

  def add(afterPart: Part, part: Part): this.type = {
    this._childs.insert(this._childs.indexOf(afterPart) + 1, part)
    this
  }

  def addBefor(beforPart: Part, part: Part): this.type = {
    this._childs.insert(this._childs.indexOf(beforPart), part)
    this
  }

  def remove(part: Part): this.type = {
    val index = this._childs.indexOf(part)

    if (index >= 0) {
      this._childs.remove(index)
    }

    this
  }

  def childs: Array[Part] = {
    this._childs.toArray
  }

  override def text(): String = {
    Option(this.childs).map(_.foldLeft("")(_ + _.text())).get
  }

  def getByType[T <: Part]()(implicit ev: ClassTag[T]): Array[T] = {
    this.childs.filter(ev.runtimeClass.isInstance(_)).foldLeft(new ArrayBuffer[T](0)) ((a, part) => a += part.asInstanceOf[T]).toArray[T]
  }

  override def recalc(start: Int): Int = {
    this.childs.foldLeft(start)((s, p) => p.recalc(s))
  }
}
