package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}

import scala.swingx.binding.ToggleButtonBinding

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait ToggleButton[T <: javax.swing.AbstractButton] {

  private def source: T = this.asInstanceOf[T]

  protected def onClick(source: T, action: () => Unit): ToggleButton[T] = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply()
    })
    this
  }

  protected def onChange(source: T, action: () => Unit): ToggleButton[T] = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(actionEvent: ActionEvent) = action.apply()
    })
    this
  }

  protected def onSelect(source: T, action: () => Unit): ToggleButton[T] =
    onChange(source, () => {
      if (source.getModel().isSelected()) action.apply()
      this
    })

  protected def onUnselect(source: T, action: () => Unit): ToggleButton[T] =
    onChange(source, () => {
      if (!source.getModel().isSelected()) action.apply()
      this
    })

}
