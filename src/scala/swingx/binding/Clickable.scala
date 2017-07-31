package scala.swingx.binding

import java.awt.event.{ActionEvent, ActionListener}

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait Clickable[T <: javax.swing.AbstractButton, U] {

  def source: T = this.asInstanceOf[T]

  def click(action: T => Unit): U = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply(source)
    })
    this.asInstanceOf[U]
  }

}
