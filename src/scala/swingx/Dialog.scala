package scala.swingx

import java.awt.event._
import javax.swing.{ImageIcon, JComponent, KeyStroke, WindowConstants}

import scala.swingx.binding.Binding
import scala.swingx.utils.SwingConstants

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Dialog[T <: javax.swing.JDialog](val swingComponent: T,
                                            var parent: Option[java.awt.Component] = Option(null)) extends Windowable {

  private var _initialize: T => Unit = swingComponent => Unit
  private var _finalize: T => Unit = swingComponent => Unit

  def from(component: javax.swing.JFrame): Dialog[T] = {
    parent = Option(component)
    this
  }

  def from(component: javax.swing.JDialog): Dialog[T] = {
    parent = Option(component)
    this
  }

  /** Asigna el titulo a la ventana
    *
    * @param title
    * @return
    */
  def title(title: String): Dialog[T] = {
    swingComponent.setTitle(title)
    this
  }

  /** Asigna un icono a la ventana de dialogo
    *
    * @param icon imagen en formato ImageIcon
    * @return
    */
  def icon(icon: ImageIcon): Dialog[T] = {
    swingComponent.setIconImage(icon.getImage)
    this
  }

  /**
    * Centra la ventana de dialogo al componente indicado, si no
    * se indica ningun componente centra en pantalla
    *
    * @return
    */
  def center(): Dialog[T] = {
    parent.map(p => swingComponent.setLocationRelativeTo(p))
    this
  }

  /** Alias para display
    *
    * @return
    */
  def show(): Dialog[T] = {
    display
    this
  }

  /** Muestra la ventana de dialogo. Realiza algunas acciones adicionales
    * como; setea la apariencia de swing a la usada por el Sistema Operativo,
    * agrega comportamiento ESCAPE de teclado para cancelar la ventana, trae
    * el dialogo al frente y le da el foco.
    */
  def display: Unit = {
    try _initialize.apply(swingComponent) catch {
      case e: Exception => println(e)
    }

    applySystemLAF(swingComponent)

    swingComponent.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(event: WindowEvent): Unit = {
        cancel
      }
    })

    swingComponent.getRootPane.registerKeyboardAction(new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        cancel
      }
    }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    swingComponent.pack
    swingComponent.setModal(true)
    swingComponent.setVisible(true)
    swingComponent.toFront
    swingComponent.requestFocusInWindow

  }

  def prepare[U](proc: (T) => Unit): Dialog[T] = {
    _initialize = proc
    this
  }

  def terminate[U](proc: (T) => Unit): Dialog[T] = {
    _finalize = proc
    this
  }

  /** Alias para dispose
    *
    */
  def cancel(): Unit = dispose

  /** Cierra la ventana de dialogo
    *
    */
  def dispose(): Unit = {
    try _finalize.apply(swingComponent) catch {
      case e: Exception => println(e)
    }
    swingComponent.dispose
  }

  def bind[U <: javax.swing.JComponent](component: U, action: () => Unit): Dialog[T] = {
    new Binding[U](component, action)
    this
  }

}

object Dialog {

  /** Obtiene un objeto Dialog a partir de un javax.swing.JFrame
    *
    * @param component
    * @return
    */
  def of[U <: javax.swing.JDialog](component: U): Dialog[U] = new Dialog[U](component)

}
