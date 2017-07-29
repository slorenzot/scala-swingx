package scala.swingx

import java.awt.Component
import java.awt.event._
import javax.swing.{ImageIcon, JComponent, KeyStroke, WindowConstants}

import scala.swingx.binding.Binding

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Dialog(val view: javax.swing.JDialog) extends Window {

  var parent: Option[Component] = Option(null)

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
    view.setTitle(title)
    this
  }

  /** Asigna un icono a la ventana de dialogo
    *
    * @param icon imagen en formato ImageIcon
    * @return
    */
  def icon(icon: ImageIcon): Dialog = {
    view.setIconImage(icon.getImage)
    this
  }

  /**
    * Centra la ventana de dialogo al componente indicado, si no
    * se indica ningun componente centra en pantalla
    *
    * @param parentTo
    * @return
    */
  def center(): Dialog = {
    parent.map(p => view.setLocationRelativeTo(p))
    this
  }

  /** Muestra la ventana de dialogo. Realiza algunas acciones adicionales
    * como; setea la apariencia de swing a la usada por el Sistema Operativo,
    * agrega comportamiento ESCAPE de teclado para cancelar la ventana, por
    * defecto centra el dialogo en la ventana que lo invoco, trae el dialogo
    * al frente y le da el foco.
    */
  def display: Unit = {
    this.defaultLAF(view)

    view.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)
    view.addWindowListener(new WindowAdapter() {
      override def windowClosing(event: WindowEvent): Unit = {
        dispose()
        cancelEvent()
      }
    })

    view.getRootPane.registerKeyboardAction(new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        dispose()
        cancelEvent()
      }
    }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    view.pack
    view.setModal(true)
    parent.map(p => view.setLocationRelativeTo(p))
    view.setVisible(true)
    view.toFront
    view.requestFocusInWindow
  }

  def dispose(): Unit = view.dispose

  def bind[U](component: U, action: () => {}): Dialog = {
    new Binding(component, action)
    this
  }

}

object Dialog {

  def of(component: javax.swing.JDialog): Dialog = new Dialog(component)

}
