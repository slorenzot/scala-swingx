package scala.swingx.binding

import scala.swingx.binding.contract.ToggleComboBox

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ComboBoxBinding(swingComponent: javax.swing.JComboBox[String]) extends Bindable
  with ToggleComboBox[javax.swing.JComboBox[String], ComboBoxBinding] {

  def onChange(action: () => Unit): ComboBoxBinding = {
    super.onChange(swingComponent, action)
    this
  }

}
