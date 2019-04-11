package biz.ostw.fsi.lang.xml

import java.nio.file.{Files, Paths}

import biz.ostw.fsi.translator.{InputStreamSource, PartDestination}
import org.junit.{Assert, Test}

class XmlTranslatorTest {
  private val url = classOf[XmlTranslatorTest].getResource("test.xml")

  @Test
  def testTranslate(): Unit = {
    val is = new InputStreamSource(this.url)
    val translator = new XmlTranslator
    val documentPart = translator.translate(is, new DocumentPartDestination)

    Assert.assertNotNull(documentPart.root.getOrElse(null))
    Assert.assertEquals("root", documentPart.root.map(_.name).getOrElse(null))

    val i = documentPart.root.map(_.childs).get.iterator
    Assert.assertTrue(i.next.isInstanceOf[TerminalPart])
    Assert.assertTrue(i.next.isInstanceOf[CommentPart])
    Assert.assertTrue(i.next.isInstanceOf[TerminalPart])
    Assert.assertTrue(i.next.isInstanceOf[ElementPart])
  }

  @Test
  def createEmptyDocTest(): Unit = {
    val documentPart = PartFactory.document(null)

    Assert.assertNull(documentPart.root.getOrElse(null))
  }

  @Test
  def createDocTest(): Unit = {
    val rootElement = PartFactory.element("root")
    val documentPart = PartFactory.document(rootElement)

    documentPart.addBefor(rootElement, PartFactory.comment("This is a test comment"))

    Assert.assertNotNull(documentPart.root.getOrElse(null))

    val i = documentPart.childs.iterator
    Assert.assertTrue(i.next.isInstanceOf[PrologPart])
    Assert.assertTrue(i.next.isInstanceOf[TerminalPart])
    Assert.assertTrue(i.next.isInstanceOf[CommentPart])
    Assert.assertTrue(i.next.isInstanceOf[ElementPart])
  }
}
