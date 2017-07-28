package scala.swingx

import java.awt.Component
import java.awt.event.{WindowEvent, WindowStateListener}
import javax.swing.ImageIcon

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Frame[T](val view: javax.swing.JFrame) extends Window {

  var previousState: Integer = view.getExtendedState

  view.addWindowStateListener(new WindowStateListener {
    override def windowStateChanged(windowEvent: WindowEvent) = {
      previousState = windowEvent.getNewState
    }
  })

  def title(title: String): Frame[T] = {
    view.setTitle(title)
    this
  }

  def icon(icon: ImageIcon): Frame[T] = {
    view.setIconImage(icon.getImage)
    this
  }

  def display: Unit = {
    this.defaultLAF(view)

    view.pack
    view.setVisible(true)
    view.toFront
    view.requestFocusInWindow
  }

  def dispose: Unit = {
    view.dispose
  }

  def fullscreen: Unit = {}

  def maximize: Frame[T] = {
    previousState = view.getExtendedState
    view.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH)
    this
  }

  def minimize: Frame[T] = {
    previousState = view.getExtendedState
    view.setExtendedState(java.awt.Frame.ICONIFIED)
    this
  }

  def restore: Frame[T] = {
    val current = view.getExtendedState
    view.setExtendedState(previousState)
    previousState = current
    this
  }

  def bind[U](component: javax.swing.JComponent, action: => {}): Frame[T] = {
    Binding.bind(component, action)
    this
  }

}

object Frame {

  def of[T](component: javax.swing.JFrame): Frame[T] = {
    new Frame[T](component);
  }

}
