package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}

import scala.swingx.binding.ToggleButtonBinding

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait ToggleButton[T <: javax.swing.AbstractButton] {

  private def source: T = this.asInstanceOf[T]

  protected def click(source: T, action: () => Unit): ToggleButton[T] = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply()
    })
    this
  }

  protected def change(source: T, action: () => Unit): ToggleButton[T] = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(actionEvent: ActionEvent) = action.apply()
    })
    this
  }

  protected def selected(source: T, action: () => Unit): ToggleButton[T] =
    change(source, () => {
      if (source.getModel().isSelected()) action.apply()
      this
    })

  protected def unselected(source: T, action: () => Unit): ToggleButton[T] =
    change(source, () => {
      if (!source.getModel().isSelected()) action.apply()
      this
    })

}
