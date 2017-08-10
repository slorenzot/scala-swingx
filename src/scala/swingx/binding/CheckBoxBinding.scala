package scala.swingx.binding

import scala.swingx.binding.contract.generic.Clickable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class CheckBoxBinding(swingComponent: javax.swing.JCheckBox) extends Bindable
  with Clickable[javax.swing.JCheckBox, CheckBoxBinding]{

  def select(isSelect: Boolean = true): CheckBoxBinding = {
    swingComponent.setSelected(isSelect)
    this
  }

  def onClick(action: () => Unit): CheckBoxBinding =  {
    super.onClick(swingComponent, action)
    this
  }

}
