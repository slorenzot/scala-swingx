package scala.swingx

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Dialog[T](val view: javax.swing.JDialog) extends WindowImpl {

  def title(title: String): Dialog[T] = {
    view.setTitle(title)
    this
  }

  override def display(): Unit = {
    defaultLAF(view)

    view.pack
    view.setVisible(true)
    view.toFront
    view.requestFocusInWindow
  }

  override def dispose() = {
    view.dispose
  }

//  def buttonAction(button: JButton, event: => {}): Dialog = {
//
//    this
//  }

}
