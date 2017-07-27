package scala.swingx

import java.awt.Component
import javax.swing.{SwingUtilities, UIManager}

/**
  * Created by Soulberto on 7/27/2017.
  */
trait WindowImpl {

  def defaultLAF(container: Component): Unit = {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    SwingUtilities.updateComponentTreeUI(container)
  }

  def display

  def dispose

  def maximize: Unit = {}

  def minimize: Unit = {}

  def restore: Unit = {}

}
