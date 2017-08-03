package scala.swingx.binding

import scala.swingx.binding.contract.Clickable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class LabelBinding(swingComponent: javax.swing.JLabel) extends Clickable[javax.swing.JLabel, LabelBinding]{

  def click(action: () => Unit): LabelBinding =  {
    super.click(swingComponent, action)
    this
  }

}
