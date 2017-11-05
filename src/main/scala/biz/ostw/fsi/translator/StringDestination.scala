package biz.ostw.fsi.translator

import java.net.URI

/**
  * Created by mathter on 05.07.17.
  */
class StringDestination extends Destination[String] {
  override def uri(): URI = new URI("translator:destination:string")
}
