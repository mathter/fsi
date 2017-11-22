package biz.ostw.fsi


/**
  * @author mathter (c) 2017.
  */
class WrapperPart[T <: Part](val part: T) extends Part {

  override def start() = this.part.start

  override def stop() = this.part.stop

  override def text() = this.part.text

  override def recalc(start: Int) = this.part.recalc(start)
}