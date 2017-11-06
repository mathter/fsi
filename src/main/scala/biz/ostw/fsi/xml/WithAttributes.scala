package biz.ostw.fsi.xml

/**
  * Created by kav on 02.08.17.
  */
trait WithAttributes {

  def attributes(): Array[AttributePart];

  def attribute(name: String, value: String): AttributePart;

  def attribute(name: String): String;
}
