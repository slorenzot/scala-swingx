package scala.swingx.binding.contract

import java.awt.Component
import javax.swing.{SwingUtilities, UIManager}

/**
  * Created by Soulberto on 7/27/2017.
  */
trait Windowable {

  def applySystemLAF(container: Component): Unit = {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    SwingUtilities.updateComponentTreeUI(container)
  }

  def display: Unit
  def dispose: Unit

}
