package biz.ostw.fsi.xml

import java.io.OutputStreamWriter

import biz.ostw.fsi.translator._
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}

/**
  * Created by mathter on 04.07.17.
  */
class XmlTranslator extends Translator {

  @throws[InvalidSourceException]
  @throws[InvalidDestinationException]
  override def translate[S, D](source: Source[S], destination: Destination[D]): D = {

    if (source.isInstanceOf[InputStreamSource]) {
      if (destination.isInstanceOf[PartDestination[_]]) {
        this.stream2part(source.asInstanceOf[InputStreamSource], destination.asInstanceOf[PartDestination[_]]).asInstanceOf[D]
      } else {
        throw new InvalidDestinationException(destination)
      }
    } else {
      if (source.isInstanceOf[PartSource]) {
        if (destination.isInstanceOf[OutputStreamDestination]) {
          val writer = new OutputStreamWriter(destination.asInstanceOf[OutputStreamDestination].outputStream)
          writer.write(source.asInstanceOf[PartSource].part.text.toString)

          destination.asInstanceOf[OutputStreamDestination].outputStream.asInstanceOf[D]
        } else {
          throw new InvalidDestinationException(destination)
        }
      } else {
        throw new InvalidSourceException(source)
      }
    }
  }

  private def stream2part(source: InputStreamSource, destination: PartDestination[_]): DocumentPart = {
    val stream = new ANTLRInputStream(source.inputStream)
    val lexer = new XmlLexer(stream)
    val commonTokenStream = new CommonTokenStream(lexer)
    val xmlParser = new XmlParser(commonTokenStream)
    val walker = new ParseTreeWalker
    val xmlProcessing = new XmlProcessing

    walker.walk(xmlProcessing, xmlParser.document)
    xmlProcessing.documentPart
  }
}
