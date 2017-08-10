package scala.swingx.binding

import java.awt.event.{ActionEvent, ActionListener}

import scala.swingx.binding.contract.generic.Clickable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ButtonBinding(swingComponent: javax.swing.JButton) extends Bindable
  with Clickable[javax.swing.JButton, ButtonBinding]{

  def onClick(action: () => Unit): ButtonBinding =  {
    super.onClick(swingComponent, action)
    this
  }

}
