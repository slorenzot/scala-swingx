package scala.swingx.binding

import scala.swingx.binding.contract.{Clickable, ToggleButton}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ToggleButtonBinding(swingComponent: javax.swing.JToggleButton) extends ToggleButton[javax.swing.JToggleButton, ToggleButtonBinding] {

  def click(action: () => Unit): ToggleButtonBinding =  {
    super.click(swingComponent, action)
    this
  }

  def change(action: () => Unit): ToggleButtonBinding = {
    super.change(swingComponent, action)
    this
  }

}
