package scala.swingx.binding

import javax.swing.JToggleButton

import scala.swingx.binding.contract.ToggleButton

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ToggleButtonBinding(swingComponent: javax.swing.JToggleButton)
  extends ToggleButton[javax.swing.JToggleButton] {

  def onClick(action: () => Unit): ToggleButtonBinding =  {
    super.onClick(swingComponent, action)
    this
  }

  def onChange(action: Boolean => Unit): ToggleButtonBinding = {
    super.onChange(swingComponent, action)
    this
  }

  def onToggle(action: Boolean => Unit): ToggleButtonBinding = this.onChange(action)

  def onSelect(action: () => Unit): ToggleButtonBinding = {
    super.onSelect(swingComponent, action)
    this
  }

  def onUnselect(action: () => Unit): ToggleButtonBinding = {
    super.onUnselect(swingComponent, action)
    this
  }

}
