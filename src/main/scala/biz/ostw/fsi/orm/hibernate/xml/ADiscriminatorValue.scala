package biz.ostw.fsi.orm.hibernate.xml

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ADiscriminatorValue extends WithAttributes {
  def discriminatorValue(): String = {
    this.attribute(AName.attr)
  }
}

object ADiscriminatorValue {
  val attr = "discriminator-value"
}