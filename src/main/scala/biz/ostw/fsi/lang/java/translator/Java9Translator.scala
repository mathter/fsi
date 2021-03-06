package biz.ostw.fsi.lang.java.translator

import java.io.OutputStreamWriter

import biz.ostw.fsi.lang.java.part.Java9CompilationUnitPart
import biz.ostw.fsi.lang.java.{Java9Lexer, Java9Parser}
import biz.ostw.fsi.translator._
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

class Java9Translator extends Translator {
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
          writer.flush

          destination.asInstanceOf[OutputStreamDestination].outputStream.asInstanceOf[D]
        } else {
          throw new InvalidDestinationException(destination)
        }
      } else {
        throw new InvalidSourceException(source)
      }
    }
  }

  private def stream2part(source: InputStreamSource, destination: PartDestination[_]): Java9CompilationUnitPart = {
    val stream = CharStreams.fromStream(source.inputStream)
    val lexer = new Java9Lexer(stream)
    val commonTokenStream = new CommonTokenStream(lexer)
    val parser = new Java9Parser(commonTokenStream)
    val walker = new ParseTreeWalker
    val processing = new Java9Processing

    parser.compilationUnit();

    walker.walk(processing, parser.compilationUnit)
    processing.compilationUnitPart
  }
}
