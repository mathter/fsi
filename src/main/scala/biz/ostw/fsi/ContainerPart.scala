package biz.ostw.fsi

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
  * Created by mathter on 23.06.17.
  */
trait ContainerPart extends Part {

  val EMPTY: Array[Part] = new Array(0)

  def add(part: Part): this.type

  def add(afterPart: Part, part: Part): this.type

  def addBefor(beforPart: Part, part: Part): this.type

  def remove(part: Part): this.type

  def childs: Array[Part]

  def getByType[T <: Part]()(implicit ev: ClassTag[T]): Array[T] = {
    Option(this.childs).map(_.filter(ev.runtimeClass.isInstance(_)).foldLeft(new ArrayBuffer[T](0)) { (array, part) => {
      array += part.asInstanceOf[T]
    }
    }.toArray[T]).getOrElse(new Array[T](0))
  }

  override def recalc(start: Int): Int = {
    if (this.childs != null) {
      this.childs.foldLeft(start) { (startIndex, part) => {
        part.recalc(startIndex)
      }
      }
    }
    else {
      start
    }
  }
}
