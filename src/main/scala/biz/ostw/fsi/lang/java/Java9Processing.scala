package biz.ostw.fsi.lang.java

import biz.ostw.fsi.lang.java.literal._
import biz.ostw.fsi.{ContainerPart, Part, WithParent}

import scala.collection.mutable

class Java9Processing extends Java9BaseListener {

  var compilationUnitPart: Java9CompilationUnitPart = _

  private val stack: mutable.ArrayStack[Part] = new mutable.ArrayStack[Part]()

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterCompilationUnit(ctx: Java9Parser.CompilationUnitContext): Unit = {
    this.stack.push(new Java9CompilationUnitPart)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitCompilationUnit(ctx: Java9Parser.CompilationUnitContext): Unit = {
    this.compilationUnitPart = this.stack.pop.asInstanceOf[Java9CompilationUnitPart]
  }


  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterOrdinaryCompilation(ctx: Java9Parser.OrdinaryCompilationContext): Unit = {
    this.pushTopAsChildAndTop(new OrdinaryCompilationUnitPart)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitOrdinaryCompilation(ctx: Java9Parser.OrdinaryCompilationContext): Unit = {
    this.stack.pop
  }



  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterLiteral(ctx: Java9Parser.LiteralContext): Unit = {
    this.pushTopAsChild(
      if (ctx.StringLiteral() != null) {
        new StringLiteralPart(ctx.StringLiteral().getSymbol.getText)
      } else if (ctx.IntegerLiteral() != null) {
        new IntegerLiteralPart(ctx.IntegerLiteral().getSymbol.getText)
      } else if (ctx.NullLiteral() != null) {
        new NullLiteralPart
      } else if (ctx.CharacterLiteral() != null) {
        new CharacterLiteralPart(ctx.CharacterLiteral().getSymbol.getText)
      } else if (ctx.BooleanLiteral() != null) {
        new BooleanLiteralPart(ctx.BooleanLiteral().getSymbol.getText)
      } else if (ctx.FloatingPointLiteral() != null) {
        new FloatingPointLiteralPart(ctx.FloatingPointLiteral().getSymbol.getText)
      } else {
        throw new IllegalStateException("Illegal literal '" + ctx.start.getText + "! Line number: " + ctx.getStart.getLine + ", start position: " + ctx.start.getStartIndex + ", end position: " + ctx.start.getStopIndex);
      }
    )
  }

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
