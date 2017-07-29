package scala.swingx.binding

import java.awt.event._

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class LabelBinding(swingComponent: javax.swing.JLabel) {

  def source = this

  def click(action: LabelBinding => {}): LabelBinding = {
    swingComponent.addMouseListener(new MouseAdapter {
      override def mouseClicked(e: MouseEvent) = action.apply(source)
    })
    this
  }

}
