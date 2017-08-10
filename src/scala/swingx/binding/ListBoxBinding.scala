package scala.swingx.binding

import scala.swingx.binding.contract.{ListBox, ToggleComboBox}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ListBoxBinding(swingComponent: javax.swing.JList[String]) extends Bindable
  with ListBox[javax.swing.JList[String], ListBoxBinding] {

  def onChange(action: () => Unit): ListBoxBinding = {
    super.onChange(swingComponent, action)
    this
  }

}
