package scala.swingx

import java.awt.event.{WindowAdapter, WindowEvent, WindowStateListener}
import javax.swing.{ImageIcon, WindowConstants}

import scala.swingx.binding.Binding

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Frame(view: javax.swing.JFrame) extends Window {

  var previousState: Integer = view.getExtendedState

  view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  view.addWindowStateListener(new WindowStateListener {
    override def windowStateChanged(windowEvent: WindowEvent) = {
      previousState = windowEvent.getNewState
    }
  })

  def title(title: String): Frame = {
    view.setTitle(title)
    this
  }

  def icon(icon: ImageIcon): Frame = {
    view.setIconImage(icon.getImage)
    this
  }

  def show: Unit = this.display

  def display: Unit = {
    this.defaultLAF(view)

    view.pack
    view.setVisible(true)
    view.toFront
    view.requestFocusInWindow
  }

  def dispose: Unit = view.dispose


  def fullscreen: Unit = {}

  def maximize: Frame = {
    previousState = view.getExtendedState
    view.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH)
    this
  }

  def minimize: Frame = {
    previousState = view.getExtendedState
    view.setExtendedState(java.awt.Frame.ICONIFIED)
    this
  }

  def restore: Frame = {
    val current = view.getExtendedState
    view.setExtendedState(previousState)
    previousState = current
    this
  }

  def opened(action: (javax.swing.JFrame) => Unit): Frame = {
    view.addWindowListener(new WindowAdapter() {
      override def windowOpened(e: WindowEvent): Unit = action.apply(view)
    })
    this
  }

  def confirmClosing(dialog: (String, String, java.awt.Component) => Int = SwingUtils.confirm): Frame = {
    view.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

    view.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent): Unit =
        if (dialog.apply("Do you want exit?", "Confirm exit", view) == OptionDialog.YES) dispose
    })
    this
  }

  def closing(action: (javax.swing.JFrame) => Unit): Frame = {
    view.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent): Unit = action.apply(view)
    })
    this
  }

  def closed(action: (javax.swing.JFrame) => Unit): Frame = {
    view.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent): Unit = action.apply(view)
    })
    this
  }

  def bind[U](component: U, action: () => Unit): Frame = {
    new Binding(component, action)
    this
  }

}

object Frame {

  def of(component: javax.swing.JFrame): Frame = new Frame(component);

}
