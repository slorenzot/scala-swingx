package scala.swingx.binding

import scala.swingx.binding.contract.generic.Clickable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class MenuItemBinding(swingComponent: javax.swing.JMenuItem)
  extends Clickable[javax.swing.JMenuItem, MenuItemBinding] {

  def click(action: () => Unit): MenuItemBinding = {
    super.click(swingComponent, action)
    this
  }

}
