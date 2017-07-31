package scala.swingx.binding

import java.awt.event.{ActionEvent, ActionListener}

import scala.swingx.binding.contract.Clickable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ButtonBinding(swingComponent: javax.swing.JButton) extends Clickable[javax.swing.JButton, ButtonBinding]{

  def click(action: () => Unit): ButtonBinding =  {
    super.click(swingComponent, action)
    this
  }

}
