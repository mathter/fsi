package biz.ostw.fsi.translator

import java.io.{File, FileOutputStream, OutputStream}
import java.net.{URI, URL}

/**
  * Created by kav on 04.07.17.
  */
class OutputStreamDestination(val outputStream: OutputStream, val uri: URI) extends Destination[OutputStream] {

  def this(url: URL) {
    this(url.openConnection().getOutputStream, url.toURI)
  }

  def this(file: File) {
    this(new FileOutputStream(file), file.toURI)
  }
}
