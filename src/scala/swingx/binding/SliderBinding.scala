package scala.swingx.binding

import javax.swing.JSlider

import scala.swingx.binding.contract.{Sliderable, TextSelectable}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class SliderBinding(swingComponent: javax.swing.JSlider) extends Bindable
  with Sliderable[javax.swing.JSlider] {

  //  def caret(action: () => Unit): TextBinding = super.caret(swingComponent, action)

  def onMove(action: Int => Unit): SliderBinding = {
    super.onMove(swingComponent, action)
    this
  }

  def whileMove(action: Int => Unit): SliderBinding = {
    super.whileMove(swingComponent, action)
    this
  }

}