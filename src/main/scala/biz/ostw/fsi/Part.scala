package biz.ostw.fsi

/**
  * Class represent a text part of the text document.
  *
  * Created by mathter on 23.06.17.
  */
trait Part {

  /**
    * Return index of first symbol of the part.
    *
    * @return index of first symbol of the part.
    */
  def start(): Int = 0

  /**
    * Return index of last symbol of the part.
    *
    * @return index of last symbol of the part.
    */
  def stop(): Int = {
    this.start + this.text.length - 1;
  }

  /**
    * Method return text representation of the part.
    * Always return not `null`.
    *
    * @return text representation of the part.
    */
  def text(): String = ""

  /**
    * Recalculate index of the first lement of the part using `start` parameter of the method.
    *
    * @param start new start index.
    * @return index of last symbol of the part.
    */
  def recalc(start: Int): Int = {
    this.start + this.text.length
  };

  override def toString: String = this.text
}