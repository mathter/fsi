package biz.ostw.fsi.xml

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
}
