package scala.swingx.binding

import javax.swing.JToggleButton

import scala.swingx.binding.contract.ToggleButton

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ToggleButtonBinding(swingComponent: javax.swing.JToggleButton)
  extends ToggleButton[javax.swing.JToggleButton] {

  def click(action: () => Unit): ToggleButtonBinding =  {
    super.click(swingComponent, action)
    this
  }

  def change(action: () => Unit): ToggleButtonBinding = {
    super.change(swingComponent, action)
    this
  }

  def toggle(action: () => Unit): ToggleButtonBinding = this.change(action)

  def selected(action: () => Unit): ToggleButtonBinding = {
    super.selected(swingComponent, action)
    this
  }

  def unselected(action: () => Unit): ToggleButtonBinding = {
    super.unselected(swingComponent, action)
    this
  }

}
