package biz.ostw.fsi.translator


/**
  * Created by mathter on 04.07.17.
  */
trait Translator {

  def translate[S, D](source: Source[S], destination: Destination[D]): D
}
