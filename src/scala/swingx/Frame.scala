package scala.swingx

import java.awt.event.{WindowAdapter, WindowEvent, WindowStateListener}
import javax.swing.{ImageIcon, WindowConstants}

import scala.swingx.binding.Binding
import scala.swingx.utils.{SwingConstants, SwingUtils}

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Frame(swingComponent: javax.swing.JFrame) extends Window {

  var lastState: Integer = swingComponent.getExtendedState

  swingComponent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  swingComponent.addWindowStateListener(new WindowStateListener {
    override def windowStateChanged(windowEvent: WindowEvent) = {
      lastState = windowEvent.getOldState
      println(lastState)
    }
  })

  def title(title: String): Frame = {
    swingComponent.setTitle(title)
    this
  }

  def icon(icon: ImageIcon): Frame = {
    swingComponent.setIconImage(icon.getImage)
    this
  }

  def show: Unit = this.display

  def display: Unit = {
    this.defaultLAF(swingComponent)

    swingComponent.pack
    swingComponent.setVisible(true)
    swingComponent.toFront
    swingComponent.requestFocusInWindow
  }

  def dispose: Unit = swingComponent.dispose


  def fullscreen: Unit = {}

  def maximize: Frame = {
    lastState = swingComponent.getExtendedState
    swingComponent.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH)
    this
  }

  def minimize: Frame = {
    lastState = swingComponent.getExtendedState
    swingComponent.setExtendedState(java.awt.Frame.ICONIFIED)
    this
  }

  def restore: Frame = {
    val current = swingComponent.getExtendedState
    swingComponent.setExtendedState(lastState)
    lastState = current
    this
  }

  def opened(action: (javax.swing.JFrame) => Unit): Frame = {
    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowOpened(e: WindowEvent): Unit = action.apply(swingComponent)
    })
    this
  }

  def confirmClosing(dialog: () => Int = () => SwingUtils.confirm("Do you want exit?", "Confirm exit", null)): Frame = {
    swingComponent.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent): Unit =
        if (dialog.apply() == SwingConstants.YES) dispose
    })
    this
  }

  def closing(action: (javax.swing.JFrame) => Unit): Frame = {
    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent): Unit = action.apply(swingComponent)
    })
    this
  }

  def closed(action: (javax.swing.JFrame) => Unit): Frame = {
    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent): Unit = action.apply(swingComponent)
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
