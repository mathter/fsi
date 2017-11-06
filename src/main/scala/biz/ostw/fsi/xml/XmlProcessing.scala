package biz.ostw.fsi.xml

import biz.ostw.fsi.{ContainerPart, Part, WithParent}
import org.antlr.v4.runtime.tree.TerminalNode

import scala.collection.mutable

/**
  * Created by kav on 24.07.17.
  */
class XmlProcessing extends XmlParserBaseListener {

  private var _documentPart: DocumentPart = null

  private var brace: Char = '"'

  private val stack: mutable.ArrayStack[Part] = new mutable.ArrayStack[Part]()

  def documentPart(): DocumentPart = {
    this._documentPart.ctx = new Context(this.brace)
    this._documentPart
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterAttributeValue(ctx: XmlParser.AttributeValueContext): Unit = {
    this.stack.top.asInstanceOf[AttributePart].value(Option(ctx.ATTRIBUTE_VALUE0()).getOrElse(ctx.ATTRIBUTE_VALUE1()).getSymbol.getText)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterAttributeBrace(ctx: XmlParser.AttributeBraceContext): Unit = {
    this.brace = Option(ctx.ATTRIBUTE_VALUE_BRACE0()).getOrElse(Option(ctx.ATTRIBUTE_VALUE_BRACE1()).getOrElse(Option(ctx.ATTRIBUTE_VALUE_BRACE01()).getOrElse(ctx.ATTRIBUTE_VALUE_BRACE11()))).getSymbol.getText.charAt(0)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterAttributeName(ctx: XmlParser.AttributeNameContext): Unit = {
    this.stack.top.asInstanceOf[AttributePart].name(ctx.ATTRIBUTE_NAME().getSymbol.getText)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterAttribute(ctx: XmlParser.AttributeContext): Unit = {
    this.pushTopAsChildAndTop(PartFactory.attribute("", ""))
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitAttribute(ctx: XmlParser.AttributeContext): Unit = {
    this.stack.pop()
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterCdata(ctx: XmlParser.CdataContext): Unit = {
    val part = PartFactory.cdata(ctx.CDATA_CHARS().toArray.foldLeft("") { (s: String, t: AnyRef) => {
      s.concat(t.asInstanceOf[TerminalNode].getSymbol.getText)
    }
    })

    this.pushTopAsChild(part)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterComment(ctx: XmlParser.CommentContext): Unit = {
    val part = PartFactory.comment(ctx.COMMENT_CHARS().toArray.foldLeft("") { (s: String, t: AnyRef) => {
      s.concat(t.asInstanceOf[TerminalNode].getSymbol.getText)
    }
    })

    this.pushTopAsChild(part)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterDocument(ctx: XmlParser.DocumentContext): Unit = {
    this.stack.push(new DocumentPart)
  }


  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitDocument(ctx: XmlParser.DocumentContext): Unit = {
    this._documentPart = this.stack.pop().asInstanceOf[DocumentPart]
    this._documentPart.recalc(0)
  }


  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterProlog(ctx: XmlParser.PrologContext): Unit = {
    this.pushTopAsChildAndTop(new PrologPart)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitProlog(ctx: XmlParser.PrologContext): Unit = {
    this.stack.pop()
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterTagWs(ctx: XmlParser.TagWsContext): Unit = {
    this.pushTopAsChild(PartFactory.terminal(ctx.TAG_WS().getSymbol.getText))
  }


  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterElement(ctx: XmlParser.ElementContext): Unit = {
    this.pushTopAsChildAndTop(new ElementPart())
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitElement(ctx: XmlParser.ElementContext): Unit = {
    this.stack.pop()
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterEmptytag(ctx: XmlParser.EmptytagContext) = {
    val elementPart = this.stack.top.asInstanceOf[ElementPart]

    elementPart.name(ctx.tagname().TAG_NAME().getSymbol.getText)
    elementPart.isFlashed(true)
    this.stack.push(elementPart._openTag)
  }


  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitEmptytag(ctx: XmlParser.EmptytagContext) = {
    this.stack.pop()
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterOpentag(ctx: XmlParser.OpentagContext): Unit = {
    val elementPart = this.stack.top.asInstanceOf[ElementPart]

    elementPart.name(ctx.tagname().TAG_NAME().getSymbol.getText)
    this.stack.push(elementPart._openTag)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitOpentag(ctx: XmlParser.OpentagContext): Unit = {
    this.stack.pop
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterTagname(ctx: XmlParser.TagnameContext): Unit = {
    val tagNameNode = ctx.TAG_NAME()

    if (tagNameNode != null) {
      this.stack.top.asInstanceOf[Named].name(ctx.TAG_NAME().getSymbol.getText)
    }
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterChardata(ctx: XmlParser.ChardataContext): Unit = {
    val part = PartFactory.terminal(Option(ctx.TEXT()).map(_.getSymbol.getText).getOrElse(""))

    this.pushTopAsChild(part)
  }


  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def enterMisc(ctx: XmlParser.MiscContext): Unit = {
    val part = new ContainerPart

    Option(ctx.TEXT()) map { (node) => {
      part.add(PartFactory.terminal(node.getSymbol.getText))
    }
    }

    this.stack.push(part)
  }

  /**
    * {@inheritDoc }
    *
    * <p>The default implementation does nothing.</p>
    */
  override def exitMisc(ctx: XmlParser.MiscContext): Unit = {
    val part: ContainerPart = this.stack.pop().asInstanceOf[ContainerPart]

    this.pushTopAsChild(PartFactory.terminal(part.text.toString))
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
