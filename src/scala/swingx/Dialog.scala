package scala.swingx

import java.awt.Component
import javax.swing.ImageIcon

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Dialog[T](val view: javax.swing.JDialog) extends Window {

//  var parent: Window = view

  def title(title: String): Dialog[T] = {
    view.setTitle(title)
    this
  }

  def icon(icon: ImageIcon): Dialog[T] = {
    view.setIconImage(icon.getImage)
    this
  }

  def center(parent: Component = null): Dialog[T] = {
    view.setLocationRelativeTo(parent)
    this
  }

  def display: Unit = {
    this.defaultLAF(view)

    view.pack
    view.setLocationRelativeTo(null)
    view.setVisible(true)
    view.toFront
    view.requestFocusInWindow
  }

  def dispose(): Unit = {
    view.dispose
  }

}

object Dialog {

  def of[T](component: javax.swing.JDialog): Dialog[T] = {
    new Dialog[T](component);
  }

}
