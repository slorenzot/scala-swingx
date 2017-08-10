package scala.swingx.binding

import scala.swingx.binding.contract.generic.Clickable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class MenuItemBinding(swingComponent: javax.swing.JMenuItem) extends Bindable
    with Clickable[javax.swing.JMenuItem, MenuItemBinding] {

  def onClick(action: () => Unit): MenuItemBinding = {
    super.onClick(swingComponent, action)
    this
  }

}
