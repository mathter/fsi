package biz.ostw.fsi.translator

import java.net.URI

/**
  * Created by mathter on 04.07.17.
  */
trait Source[T] {
  def uri(): URI
}
