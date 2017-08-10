package scala.swingx

import java.awt.Component
import java.awt.event.{WindowAdapter, WindowEvent, WindowStateListener}
import javax.swing.{ImageIcon, WindowConstants}

import scala.swingx.binding.{Bindable, Binding}
import scala.swingx.binding.contract.Windowable
import scala.swingx.utils.{SwingConstants, SwingUtils}
import scala.util.Try

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Frame[T <: javax.swing.JFrame](val swingComponent: T,
                                          var parent: Option[Component] = Option(null)) extends Windowable {

  protected var lastState: Integer = swingComponent.getExtendedState

  private var _initialize: T => Unit = swingComponent => Unit
  private var _finalize: T => Unit = swingComponent => Unit

  swingComponent.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)

  swingComponent.addWindowStateListener(new WindowStateListener {
    override def windowStateChanged(windowEvent: WindowEvent) = lastState = windowEvent.getOldState

  })

  def title(title: String): Frame[T] = {
    swingComponent.setTitle(title)
    this
  }

  def icon(icon: ImageIcon): Frame[T] = {
    swingComponent.setIconImage(icon.getImage)
    this
  }

  def show(): Unit = this.display

  def display: Unit = {
    try _initialize.apply(swingComponent) catch {
      case e: Exception => println(e)
    }

    applySystemLAF(swingComponent)

    swingComponent.pack
    swingComponent.setVisible(true)
    swingComponent.toFront
    swingComponent.requestFocusInWindow
  }

  def closeOperation(operation: Int =WindowConstants.HIDE_ON_CLOSE) = swingComponent.setDefaultCloseOperation(operation)

  def close(confirm: Boolean = false): Unit = if (confirm) confirmClosing() else dispose

  def dispose: Unit = {
    try _finalize.apply(swingComponent) catch {
      case e: Exception => println(e)
    }
    swingComponent.dispose
  }

  def center: Frame[T] = {
    swingComponent.setLocationRelativeTo(null)
    this
  }

  def fullscreen: Unit = {}

  def maximize: Frame[T] = {
    lastState = swingComponent.getExtendedState
    swingComponent.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH)
    this
  }

  def minimize: Frame[T] = {
    lastState = swingComponent.getExtendedState
    swingComponent.setExtendedState(java.awt.Frame.ICONIFIED)
    this
  }

  def restore: Frame[T] = {
    val state = swingComponent.getExtendedState
    swingComponent.setExtendedState(lastState)
    lastState = state
    this
  }

  def opened[U](action: (T) => Unit): Frame[T] = {
    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowOpened(e: WindowEvent) = action.apply(swingComponent)
    })
    this
  }

  def confirmClosing[U](dialog: () => Int = () => SwingUtils.confirm("Do you want exit?", "Confirm exit", null)): Frame[T] = {
    swingComponent.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent) =
        if (dialog.apply() == SwingConstants.YES) dispose
    })
    this
  }

  def closing[U](action: (T) => Unit): Frame[T] = {
    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent) = action.apply(swingComponent)
    })
    this
  }

  def closed[U](action: (T) => Unit): Frame[T] = {
    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(e: WindowEvent) = action.apply(swingComponent)
    })
    this
  }

  def prepare[U](proc: (T) => Unit): Frame[T] = {
    _initialize = proc
    this
  }

  def terminate[U](proc: (T) => Unit): Frame[T] = {
    _finalize = proc
    this
  }

//  def bind[U <: javax.swing.JComponent, V](component: U): Bindable[U] = {
//    Binding.of(classOf[component])
//  }

}

object Frame {

  /** Obtiene un objeto Frame a partir de un javax.swing.JFrame
    *
    * @param component
    * @tparam U
    * @return
    */
  def of[U <: javax.swing.JFrame](component: U): Frame[U] = new Frame[U](component)

  def of[U <: javax.swing.JFrame](clazz: Class[U]): Frame[U] = {
    try new Frame[U](clazz.newInstance())
  }

}
