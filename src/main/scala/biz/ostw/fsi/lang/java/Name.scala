package biz.ostw.fsi.lang.java

import scala.collection.SeqLike


trait Name extends SeqLike[Name, Name] with Serializable with Cloneable {

  def name(): String

  def name(value: String): Unit

  def parent(): Name = apply(length - 1)
}
