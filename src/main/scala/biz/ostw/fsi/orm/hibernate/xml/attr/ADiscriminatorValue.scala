package biz.ostw.fsi.orm.hibernate.xml.attr

import biz.ostw.fsi.xml.WithAttributes

/**
  * @author mathter (c) 2017.
  */
trait ADiscriminatorValue extends WithAttributes {
  def discriminatorValue(): String = {
    this.attribute(ADiscriminatorValue.attr)
  }
}

object ADiscriminatorValue {
  val attr = "discriminator-value"
}