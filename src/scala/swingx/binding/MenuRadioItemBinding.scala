package scala.swingx.binding

import scala.swingx.binding.contract.{ToggleButton, ToggleMenuItem}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class MenuRadioItemBinding(swingComponent: javax.swing.JRadioButtonMenuItem) extends Bindable
    with ToggleMenuItem[javax.swing.JRadioButtonMenuItem, MenuRadioItemBinding] {

  def onChange(action: () => Unit): MenuRadioItemBinding = {
    super.onChange(swingComponent, action)
  }

}
