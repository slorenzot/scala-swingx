package scala.swingx

import java.awt.Component
import java.awt.event._
import javax.swing.{ImageIcon, JComponent, KeyStroke, WindowConstants}

import scala.swingx.binding.Binding

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Dialog(swingComponent: javax.swing.JDialog) extends Window {

  var parent: Option[Component] = Option(null)
  private var initialize: javax.swing.JDialog => Unit = swingComponent => {}: Unit

  var okEvent = () => println("OK")
  var cancelEvent = () => println("Cancelled by user")

  def from(component: javax.swing.JFrame): Dialog = {
    parent = Option(component)
    this
  }

  def from(component: javax.swing.JDialog): Dialog = {
    parent = Option(component)
    this
  }

  def title(title: String): Dialog = {
    swingComponent.setTitle(title)
    this
  }

  /** Asigna un icono a la ventana de dialogo
    *
    * @param icon imagen en formato ImageIcon
    * @return
    */
  def icon(icon: ImageIcon): Dialog = {
    swingComponent.setIconImage(icon.getImage)
    this
  }

  /**
    * Centra la ventana de dialogo al componente indicado, si no
    * se indica ningun componente centra en pantalla
    *
    * @return
    */
  def center(): Dialog = {
    parent.map(p => swingComponent.setLocationRelativeTo(p))
    this
  }

  def show(): Dialog = {
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
    initialize.apply(swingComponent)

    applySystemLAF(swingComponent)

    swingComponent.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)

    swingComponent.addWindowListener(new WindowAdapter() {
      override def windowClosing(event: WindowEvent): Unit = {
        dispose()
        cancelEvent()
      }
    })

    swingComponent.getRootPane.registerKeyboardAction(new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        dispose()
        cancelEvent()
      }
    }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    swingComponent.pack
    swingComponent.setModal(true)
    swingComponent.setVisible(true)
    swingComponent.toFront
    swingComponent.requestFocusInWindow
  }

  def dispose(): Unit = swingComponent.dispose

  def bind[U](component: U, action: () => Unit): Dialog = {
    new Binding(component, action)
    this
  }

}

object Dialog {

  def of(component: javax.swing.JDialog): Dialog = new Dialog(component)

}
