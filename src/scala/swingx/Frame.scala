package scala.swingx

/**
  * Created by Soulberto on 7/27/2017.
  */
case class Frame[T](val view: javax.swing.JFrame) extends WindowImpl {

  def title(title: String): Frame[T] = {
    view.setTitle(title)
    this
  }

  override def display(): Unit = {
    this.defaultLAF(view)

    view.pack
    view.setVisible(true)
    view.toFront
    view.requestFocusInWindow
  }

  override def dispose(): Unit = {
    view.dispose
  }

  def fullscreen(): Unit = {}

}
