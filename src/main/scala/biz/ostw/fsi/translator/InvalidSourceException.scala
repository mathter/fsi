package biz.ostw.fsi.translator

/**
  * Created by mathter on 04.07.17.
  */
class InvalidSourceException(val source: Source[_]) extends Exception("Invalid source: " + source) {
}
