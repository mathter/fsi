package biz.ostw.fsi

/**
  * Created by mathter on 23.06.17.
  */
trait Part {

  def start(): Int

  def stop(): Int

  def text(): String

  def recalc(start: Int): Int;
}