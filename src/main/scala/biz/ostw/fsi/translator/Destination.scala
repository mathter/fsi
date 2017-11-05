package biz.ostw.fsi.translator

import java.net.URI

/**
  * Created by mathter on 04.07.17.
  */
trait Destination[T] {
  def uri(): URI
}
