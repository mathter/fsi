package biz.ostw.fsi.lang.java

import biz.ostw.fsi.translator.InputStreamSource
import org.junit.Test

class Java9ProcessingTest {

  @Test
  def testCompilationUnit(): Unit = {
    val is = new InputStreamSource(Java9ProcessingTest.url)
    val translator = new Java9Translator
    val compilationUnitPart = translator.translate(is, new JavaCompilationUnitPartDestination)

    println(compilationUnitPart)
  }
}

object Java9ProcessingTest {
  private val url = classOf[Java9ProcessingTest].getResource("Java9t0.java")
}