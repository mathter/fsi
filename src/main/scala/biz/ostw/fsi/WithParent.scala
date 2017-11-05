package biz.ostw.fsi

/**
  * Created by mathter on 02.07.17.
  */
trait WithParent {

  def parent(): Part

  def parent(parent: Part): Unit
}
