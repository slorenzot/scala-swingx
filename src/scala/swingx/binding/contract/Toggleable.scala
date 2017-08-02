package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener, ItemEvent, ItemListener}

/**
  * Created by Soulberto Lorenzo on 7/31/2017.
  */
trait Toggleable[T <: javax.swing.AbstractButton, U] {

  private def source: T = this.asInstanceOf[T]

  protected def change(source: T, action: () => Unit): U = {
    source.addActionListener(new ActionListener {
      override def actionPerformed(actionEvent: ActionEvent) = action.apply()
    })
    this.asInstanceOf[U]
  }

  protected def selected(source: T, action: () => Unit): U = change(source, () => {
    if (source.getModel().isSelected()) action.apply()
    this
  })

  protected def unselected(source: T, action: () => Unit): U = change(source, () => {
    if (!source.getModel().isSelected()) action.apply()
    this
  })

}
