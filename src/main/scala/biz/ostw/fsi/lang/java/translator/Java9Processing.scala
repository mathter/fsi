package biz.ostw.fsi.lang.java.translator

import biz.ostw.fsi.lang.java.part.Java9CompilationUnitPart
import biz.ostw.fsi.lang.java.part.pack.PackageDeclarationPart
import biz.ostw.fsi.lang.java.{Java9BaseListener, Java9Parser}
import biz.ostw.fsi.{ContainerPart, Part, WithParent}

import scala.collection.mutable

class Java9Processing extends Java9BaseListener {

  var compilationUnitPart: Java9CompilationUnitPart = _

  private val stack: mutable.ArrayStack[Part] = new mutable.ArrayStack[Part]()

  override def enterCompilationUnit(ctx: Java9Parser.CompilationUnitContext): Unit = {
    this.stack.push(new Java9CompilationUnitPart)
  }

  override def exitCompilationUnit(ctx: Java9Parser.CompilationUnitContext): Unit = {
    this.compilationUnitPart = this.stack.pop.asInstanceOf[Java9CompilationUnitPart]
  }


  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterPackageDeclaration(ctx: Java9Parser.PackageDeclarationContext): Unit = {
    this.pushTopAsChildAndTop(new PackageDeclarationPart)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitPackageDeclaration(ctx: Java9Parser.PackageDeclarationContext): Unit = {
    this.stack.pop
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterNormalAnnotation(ctx: Java9Parser.NormalAnnotationContext): Unit = super.enterNormalAnnotation(ctx)

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitNormalAnnotation(ctx: Java9Parser.NormalAnnotationContext): Unit = super.exitNormalAnnotation(ctx)

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterFieldDeclaration(ctx: Java9Parser.FieldDeclarationContext): Unit = super.enterFieldDeclaration(ctx)

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */

  def pushTopAsChildAndTop[T <: Part](part: T): Unit = {
    val parent: ContainerPart = this.stack.top.asInstanceOf[ContainerPart]

    if (part.isInstanceOf[WithParent]) {
      part.asInstanceOf[WithParent].parent(parent)
    }

    parent.add(part)

    this.stack.push(part)
  }

  def pushTopAsChild[T <: Part](part: T): Unit = {
    val parent: ContainerPart = this.stack.top.asInstanceOf[ContainerPart]

    if (part.isInstanceOf[WithParent]) {
      part.asInstanceOf[WithParent].parent(parent)
    }

    parent.add(part)
  }
}
