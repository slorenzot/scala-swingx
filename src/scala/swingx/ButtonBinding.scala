package scala.swingx

import java.awt.event.{ActionEvent, ActionListener}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class ButtonBinding(val swingControl: javax.swing.JButton) {

  def source = this

  def click(action: ButtonBinding => {}): ButtonBinding = {
    swingControl.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) = action.apply(source)
    })
    this
  }

}
