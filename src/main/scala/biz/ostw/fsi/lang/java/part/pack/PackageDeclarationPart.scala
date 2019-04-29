package biz.ostw.fsi.lang.java.part.pack

import biz.ostw.fsi.lang.java.part.annotation.AnnotationsPart
import biz.ostw.fsi.lang.java.{Name, PackageDeclaration, PackageModifier}

class PackageDeclarationPart extends AnnotationsPart with PackageDeclaration {

  override def packageModifiers(): Array[PackageModifier] = ???

  override def name(): Name = ???
}