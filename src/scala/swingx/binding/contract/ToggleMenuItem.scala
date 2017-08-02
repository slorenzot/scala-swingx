package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}

/**
  * Created by Soulberto on 8/2/2017.
  */
trait ToggleMenuItem[T <: javax.swing.AbstractButton, U] {
  private def source: T = this.asInstanceOf[T]

  protected def click(source: T, action: () => Unit): U = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply()
    })

    this.asInstanceOf[U]
  }

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
