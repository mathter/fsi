package biz.ostw.fsi.lang.java

trait PackageDeclaration {

  def packageModifiers(): Array[PackageModifier]

  def name(): Name
}
