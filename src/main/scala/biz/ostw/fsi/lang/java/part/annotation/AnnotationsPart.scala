package biz.ostw.fsi.lang.java.part.annotation

import biz.ostw.fsi.ContainerPart

class AnnotationsPart extends ContainerPart {

  override def text(): String = {
    "@" + super.text()
  }
}
