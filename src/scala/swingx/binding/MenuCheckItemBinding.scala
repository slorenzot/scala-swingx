package scala.swingx.binding

import javax.swing.JCheckBoxMenuItem

import scala.swingx.binding.contract.{Clickable, Toggleable}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class MenuCheckItemBinding(swingComponent: javax.swing.JCheckBoxMenuItem)
  extends Toggleable[javax.swing.JCheckBoxMenuItem, MenuCheckItemBinding] {

  def change(action: () => Unit): MenuCheckItemBinding = {
    super.change(swingComponent, action)
  }

}
