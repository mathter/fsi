package biz.ostw.fsi.lang.java

import biz.ostw.fsi.lang.java.part.common.TextPart
import org.junit.{Assert, Test}

class TextPartTest {


  @Test
  def test(): Unit = {
    val tp = new TextPart(TextPartTest.START_ONE, TextPartTest.TEST_STRING)

    Assert.assertEquals(TextPartTest.START_ONE, tp.start)
    Assert.assertEquals(TextPartTest.START_ONE + TextPartTest.TEST_STRING.length - 1, tp.stop)
    Assert.assertEquals(TextPartTest.TEST_STRING, tp.text)

    Assert.assertEquals(TextPartTest.START_TWO + TextPartTest.TEST_STRING.length, tp.recalc(TextPartTest.START_TWO))
    Assert.assertEquals(TextPartTest.START_TWO, tp.start)
    Assert.assertEquals(TextPartTest.TEST_STRING, tp.text)
  }
}

object TextPartTest {

  val TEST_STRING = "hello"

  val START_ONE = 10

  val START_TWO = 30
}
