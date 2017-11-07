package biz.ostw.fsi.translator

import java.net.URI

import biz.ostw.fsi.Part

/**
  * Created by mathter on 04.07.17.
  */
class PartDestination[T <: Part] extends Destination[T] {

  override def uri(): URI = new URI("translator:destination:part/")
}