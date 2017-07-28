package scala.swingx

import java.awt.{Component, Image}
import javax.swing.{Icon, ImageIcon}

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Dialog[T](val view: javax.swing.JDialog) extends Window {

  def title(title: String): Dialog[T] = {
    view.setTitle(title)
    this
  }

  def icon(icon: Icon): Dialog[T] = {
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

//  def buttonAction(button: JButton, event: => {}): Dialog = {
//
//    this
//  }

}

object Dialog {
  def of[T](component: javax.swing.JDialog): Dialog[T] = {
    new Dialog[T](component);
  }
}
