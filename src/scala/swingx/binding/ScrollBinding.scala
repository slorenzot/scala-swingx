package scala.swingx.binding

import scala.swingx.binding.contract.Scrollable

/**
  * Created by Soulberto on 8/9/2017.
  */
case class ScrollBinding(swingComponent: javax.swing.JScrollPane) extends Scrollable[javax.swing.JScrollPane] {

  def onChange(action: (Int, Int, Int) => Unit): ScrollBinding = {
    super.onChange(swingComponent, action)
    this
  }

}
