package scala.swingx.binding

import scala.swingx.binding.contract.Toggleable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class MenuRadioItemBinding(swingComponent: javax.swing.JRadioButtonMenuItem)
  extends Toggleable[javax.swing.JRadioButtonMenuItem, MenuRadioItemBinding] {

  def change(action: () => Unit): MenuRadioItemBinding = {
    super.change(swingComponent, action)
  }

}
