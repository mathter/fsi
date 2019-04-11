package biz.ostw.fsi.lang.java

case class Separator(val value: String) {
  def apply(value: String): Separator = new Separator(value)
}

object Separator {
  val LPAREN = Separator("(")

  val RPAREN = Separator(")")

  val LBRACE = Separator("{")

  val RBRACE = Separator("}")

  val LBRACK = Separator("[")

  val RBRACK = Separator("]")

  val SEMI = Separator(";")

  val COMMA = Separator(",")

  val DOT = Separator(".")

  val ELLIPSIS = Separator("...")

  val AT = Separator("@")

  val COLONCOLON = Separator("::")
}
