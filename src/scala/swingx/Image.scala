package scala.swingx

import javax.imageio.ImageIO
import javax.swing.ImageIcon

import scala.reflect.io.Path

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Image(path: Path) {

  def toIcon: ImageIcon = {
    new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(path.toString())))
  }

}

object Image {

  def file(path: Path): Image = new Image(path)

}
