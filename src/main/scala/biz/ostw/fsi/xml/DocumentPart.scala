package biz.ostw.fsi.xml

import biz.ostw.fsi.ContainerPart

/**
  * Created by mathter on 28.06.17.
  */
class DocumentPart(var ctx: Context = new Context) extends ContainerPart {
  def root(): Option[ElementPart] = {
    this.getByType[ElementPart].headOption
  }
}
