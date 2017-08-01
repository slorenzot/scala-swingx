package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait Clickable[T <: javax.swing.AbstractButton, U] {

  protected def source: T = this.asInstanceOf[T]

  protected def click(source: T, action: () => Unit): U = {
    source.addActionListener(new ActionListener() {
        override def actionPerformed(e: ActionEvent) = action.apply()
      })

    this.asInstanceOf[U]
  }

}
