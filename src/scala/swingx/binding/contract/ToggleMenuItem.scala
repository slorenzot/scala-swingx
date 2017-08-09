package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}

/**
  * Created by Soulberto on 8/2/2017.
  */
trait ToggleMenuItem[T <: javax.swing.AbstractButton, U] {
  private def source: T = this.asInstanceOf[T]

  protected def onClick(source: T, action: () => Unit): U = {
    source.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply()
    })

    this.asInstanceOf[U]
  }

  protected def onChange(source: T, action: () => Unit): U = {
    source.addActionListener(new ActionListener {
      override def actionPerformed(actionEvent: ActionEvent) = action.apply()
    })
    this.asInstanceOf[U]
  }

  protected def onSelect(source: T, action: () => Unit): U = onChange(source, () => {
    if (source.getModel().isSelected()) action.apply()
    this
  })

  protected def onUnselect(source: T, action: () => Unit): U = onChange(source, () => {
    if (!source.getModel().isSelected()) action.apply()
    this
  })
}
