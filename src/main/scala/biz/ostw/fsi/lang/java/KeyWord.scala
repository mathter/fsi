package biz.ostw.fsi.lang.java

case class KeyWord(val value: String) {
  def apply(value: String): KeyWord = new KeyWord(value)
}

object KeyWord {
  val ABSTRACT = KeyWord("abstract")

  val ASSERT = KeyWord("assert")

  val BOOLEAN = KeyWord("boolean")

  val BREAK = KeyWord("break")

  val BYTE = KeyWord("BYTE")

  val CASE = KeyWord("case")

  val CATCH = KeyWord("catch")

  val CHAR = KeyWord("char")

  val CLASS = KeyWord("class")

  val CONST = KeyWord("const")

  val CONTINUE = KeyWord("continue")

  val DEFAULT = KeyWord("default")

  val DO = KeyWord("do")

  val DOUBLE = KeyWord("double")

  val ELSE = KeyWord("else")

  val ENUM = KeyWord("enum")

  val EXTENDS = KeyWord("extends")

  val FINAL = KeyWord("final")

  val FINALLY = KeyWord("finally")

  val FLOAT = KeyWord("float")

  val FOR = KeyWord("for")

  val IF = KeyWord("if")

  val GOTO = KeyWord("goto")

  val IMPLEMENTS = KeyWord("implements")

  val IMPORT = KeyWord("import")

  val INSTANCEOF = KeyWord("instanceof")

  val INT = KeyWord("int")

  val INTERFACE = KeyWord("interface")

  val LONG = KeyWord("long")

  val NATIVE = KeyWord("native")

  val NEW = KeyWord("new")

  val PACKAGE = KeyWord("package")

  val PRIVATE = KeyWord("private")

  val PROTECTED = KeyWord("protected")

  val PUBLIC = KeyWord("public")

  val RETURN = KeyWord("return")

  val SHORT = KeyWord("short")

  val STATIC = KeyWord("static")

  val STRICTFP = KeyWord("strictfp")

  val SUPER = KeyWord("super")

  val SWITCH = KeyWord("switch")

  val SYNCHRONIZED = KeyWord("synchronized")

  val THIS = KeyWord("this")

  val THROW = KeyWord("throw")

  val THROWS = KeyWord("throws")

  val TRANSIENT = KeyWord("transient")

  val TRY = KeyWord("try")

  val VOID = KeyWord("void")

  val VOLATILE = KeyWord("volatile")

  val WHILE = KeyWord("while")
}
