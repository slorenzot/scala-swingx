package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener, ItemEvent, ItemListener}

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait ToggleComboBox[T <: java.awt.ItemSelectable, U] {

  private def source: T = this.asInstanceOf[T]

  protected def change(source: T, action: () => Unit): U = {
    source.addItemListener(new ItemListener {
      override def itemStateChanged(itemEvent: ItemEvent) = {
        if (itemEvent.getStateChange == ItemEvent.SELECTED) action.apply()
      }
    })
    this.asInstanceOf[U]
  }

}
