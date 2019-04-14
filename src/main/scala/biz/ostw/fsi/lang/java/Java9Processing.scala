package biz.ostw.fsi.lang.java

import biz.ostw.fsi.{ContainerPart, Part, WithParent}
import org.antlr.v4.runtime.ParserRuleContext

import scala.collection.{JavaConverters, mutable}

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
