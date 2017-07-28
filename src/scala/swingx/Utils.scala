package scala.swingx

import java.awt.Component
import javax.swing.JOptionPane

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
object Utils {

  def alert(message: String, title: String = "Alert", component: Component = null): Unit =
    JOptionPane.showMessageDialog(component, message, title, JOptionPane.WARNING_MESSAGE)

  def error(message: String, title: String = "Alert", component: Component = null): Unit =
    JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE)

}
