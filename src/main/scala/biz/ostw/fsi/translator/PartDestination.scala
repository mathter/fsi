package biz.ostw.fsi.translator

import java.net.URI

import biz.ostw.fsi.Part

/**
  * Created by mathter on 04.07.17.
  */
class PartDestination extends Destination[Part] {
  override def uri(): URI = new URI("translator:destination:part")
}