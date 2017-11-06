package biz.ostw.fsi.xml

/**
  * Created by mathter on 03.08.17.
  */
trait Named {

  def name(): String;

  def name(name: String): Unit;
}