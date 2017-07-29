package scala.swingx.binding

import java.awt.event.{ActionEvent, ActionListener}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ButtonBinding(swingComponent: javax.swing.JButton) {

  def source = this

  def click(action: ButtonBinding => {}): ButtonBinding = {
    swingComponent.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply(source)
    })
    this
  }

}
