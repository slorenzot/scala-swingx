package scala.swingx.binding

import scala.swingx.binding.contract.Clickable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class MenuCheckItemBinding(swingComponent: javax.swing.JMenuItem) extends Clickable[javax.swing.JMenuItem, MenuCheckItemBinding] {

  def click(action: () => Unit): MenuCheckItemBinding = {
    super.click(swingComponent, action)
    this
  }

}
