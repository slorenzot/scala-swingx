package scala.swingx

import java.awt.Component
import java.awt.event._
import javax.swing.{ImageIcon, JComponent, KeyStroke, WindowConstants}

import scala.swingx.binding.Binding

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Dialog[T <: javax.swing.JDialog](val swingComponent: T,
                                            var parent: Option[Component] = Option(null)) extends Window {

  private var _initialize: T => Unit = swingComponent => Unit

  private var _okEvent = () => println("OK")
  private var _cancelEvent = () => println("Cancelled by user")

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
    * agrega comportamiento ESCAPE de teclado para cancelar la ventana, por
    * defecto centra el dialogo en la ventana que lo invoco, trae el dialogo
    * al frente y le da el foco.
    */
  def display: Unit = {
    _initialize.apply(swingComponent)

    applySystemLAF(swingComponent)

    swingComponent.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(event: WindowEvent): Unit = {
        dispose()
        _cancelEvent()
      }
    })

    swingComponent.getRootPane.registerKeyboardAction(new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        dispose()
        _cancelEvent()
      }
    }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    swingComponent.pack
    swingComponent.setModal(true)
    swingComponent.setVisible(true)
    swingComponent.toFront
    swingComponent.requestFocusInWindow
  }

  def prepare(proc: T => Unit): Dialog[T] = {
    _initialize = proc
    this
  }

  def dispose(): Unit = swingComponent.dispose

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
