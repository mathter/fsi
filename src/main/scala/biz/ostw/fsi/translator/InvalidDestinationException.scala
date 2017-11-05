package biz.ostw.fsi.translator

/**
  * Created by kav on 04.07.17.
  */
class InvalidDestinationException(val destination: Destination[_]) extends Exception("Invalid destination: " + destination) {
}
