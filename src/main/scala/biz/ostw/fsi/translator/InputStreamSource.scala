package biz.ostw.fsi.translator

import java.io.{File, FileInputStream, InputStream}
import java.net.{URI, URL}
import java.nio.file.Path

/**
  * Created by mathter on 04.07.17.
  */
class InputStreamSource(val inputStream: InputStream, val uri: URI) extends Source[InputStreamSource] {

  def this(file: File) {
    this(new FileInputStream(file), file.toURI)
  }

  def this(path: Path) {
    this(new FileInputStream(path.toFile), path.toUri)
  }

  def this(url: URL) {
    this(url.openStream(), url.toURI)
  }
}
