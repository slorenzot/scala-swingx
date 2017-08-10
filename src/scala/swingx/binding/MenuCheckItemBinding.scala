package scala.swingx.binding

import javax.swing.JCheckBoxMenuItem

import scala.swingx.binding.contract.{ToggleButton, ToggleMenuItem}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class MenuCheckItemBinding(swingComponent: javax.swing.JCheckBoxMenuItem) extends Bindable
  with ToggleMenuItem[javax.swing.JCheckBoxMenuItem, MenuCheckItemBinding] {

  def onChange(action: () => Unit): MenuCheckItemBinding = {
    super.onChange(swingComponent, action)
  }

}
