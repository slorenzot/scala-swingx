package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}

import scala.swingx.binding.ToggleButtonBinding

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait ToggleButton[T <: javax.swing.AbstractButton, U] extends Toggleable[T, U] {

  protected def click(source: T, action: () => Unit): U = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply()
    })

    this.asInstanceOf[U]
  }

}
