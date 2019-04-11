package biz.ostw.fsi.lang.xml

/**
  * Created by mathter on 05.11.17.
  */
object PartFactory {
  def terminal(text: String): TerminalPart = {
    new TerminalPart(text)
  }

  def attribute(name: String, value: String)(implicit ctx: Context = new Context()): AttributePart = {
    new AttributePart(name, value, ctx.brace)
  }

  def cdata(text: String): CDataPart = {
    new CDataPart(text)
  }

  def comment(text: String): CommentPart = {
    new CommentPart(text)
  }

  def prolog(encoding: String = "UTF-8", version: String = "1.0"): PrologPart = {
    val prologPart = new PrologPart
    prologPart.encoding(encoding)
    prologPart.version(version)

    prologPart
  }

  def document(root: ElementPart, encoding: String = "UTF-8", version: String = "1.0"): DocumentPart = {
    val documentPart = new DocumentPart

    documentPart.add(PartFactory.prolog(encoding, version))
    documentPart.add(PartFactory.terminal("\n\t"))

    if (root != null) {
      documentPart.add(root)
    }

    documentPart
  }

  def element(name: String): ElementPart = {
    new ElementPart(name)
  }
}
