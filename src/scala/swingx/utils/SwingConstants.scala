package scala.swingx.utils

import scala.swingx.Image

/**
  * Created by Soulberto on 7/28/2017.
  */
object SwingConstants {

  val DEFAULT_ICON = Image.file("/resources/icons/default.png").toIcon

  val YES: Int = 0
  val NO: Int = 1
  val CANCEL: Int = 2

  def option(option: Int): Option[String] = option match {
    case 0 => Option("YES")
    case 1 => Option("NO")
    case 2 => Option("CANCEL")
    case _ => None
  }


  val NORMAL: Int = 0
  val MAXIMIZED: Int = 6
  val MINIMIZED: Int = 1

  def state(state: Int): Option[String] = state match {
    case 0 => Option("NORMAL")
    case 1 => Option("MINIMIZED")
    case 6 => Option("MAXIMIZED")
    case _ => None
  }

}
