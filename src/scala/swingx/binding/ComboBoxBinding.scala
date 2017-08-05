package scala.swingx.binding

import scala.swingx.binding.contract.ToggleComboBox

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ComboBoxBinding(swingComponent: javax.swing.JComboBox[String]) extends ToggleComboBox[javax.swing.JComboBox[String], ComboBoxBinding]{

  def change(action: () => Unit): ComboBoxBinding =  {
    super.change(swingComponent, action)
    this
  }

}
