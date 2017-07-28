package scala.swingx

import java.awt.Component
import javax.swing.{ImageIcon, JOptionPane}

import scala.reflect.io.File

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
object Utils {

  def alert(message: String, title: String = "Alert", component: Component = null): Unit =
    JOptionPane.showMessageDialog(component, message, title, JOptionPane.WARNING_MESSAGE,
      Image.file("/resources/icons/Warning_48px.png").toIcon)

  def error(message: String, title: String = "Alert", component: Component = null): Unit =
    JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE,
      Image.file("/resources/icons/Error_48px.png").toIcon)

  def confirm(message: String, title: String = "Confirmar", component: Component = null, yes: Int = 0): Unit =
    JOptionPane.showConfirmDialog(component, message, title, JOptionPane.YES_NO_CANCEL_OPTION, yes,
      Image.file("/resources/icons/Help_48px.png").toIcon)


  def confirmCancel(message: String, title: String = "Confirmar", component: Component = null, yes: Int = 0): Unit =
    JOptionPane.showConfirmDialog(component, message, title, JOptionPane.YES_NO_CANCEL_OPTION, yes,
      Image.file("/resources/icons/Help_48px.png").toIcon)

}
