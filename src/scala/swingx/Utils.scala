package scala.swingx

import java.awt.Component
import javax.swing.{ImageIcon, JDialog, JOptionPane}

import scala.reflect.io.File

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
object Utils {

  val WARNING_ICON = "/resources/icons/Warning_48px.png"
  val CONFIRM_ICON = "/resources/icons/Help_48px.png"
  val ERROR_ICON = "/resources/icons/Error_48px.png"

  def alert(message: String, title: String = "Alert", component: Component = null): Unit = {
    JOptionPane.showMessageDialog(component, message, title, JOptionPane.WARNING_MESSAGE,
      Image.file(WARNING_ICON).toIcon)
  }

  def error(message: String, title: String = "Alert", component: Component = null): Unit = {
    JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE,
      Image.file(ERROR_ICON).toIcon)
  }

  def confirm(message: String,
              title: String = "Confirmar",
              component: Component = null): Int = {
    JOptionPane.showConfirmDialog(component, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
      Image.file(CONFIRM_ICON).toIcon)
  }

  def confirmCancel(message: String,
                    title: String = "Confirmar",
                    component: Component = null): Int = {
    JOptionPane.showConfirmDialog(component, message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
      Image.file(CONFIRM_ICON).toIcon)
  }

  def input(message: String,
            title: String = "Ingrese",
            component: Component = null): Option[String] = {
    Option(JOptionPane.showInputDialog(component, message, title, JOptionPane.QUESTION_MESSAGE,
      Image.file(CONFIRM_ICON).toIcon))
//    val pane = new JOptionPane(
//      "Message",
//      JOptionPane.QUESTION_MESSAGE,
//      JOptionPane.DEFAULT_OPTION
//    )
//
//    val dialog = pane.createDialog("Dialog Title")
//
//    dialog.setIconImage(new ImageIcon(Image.file(CONFIRM_ICON)))
//    dialog.setVisible(true)
  }

}
