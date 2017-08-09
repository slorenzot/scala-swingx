package scala.swingx.binding

import javax.swing.JSlider

import scala.swingx.binding.contract.{Slider, TextSelectable}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class SliderBinding(swingComponent: javax.swing.JSlider) extends Slider[javax.swing.JSlider] {

  //  def caret(action: () => Unit): TextBinding = super.caret(swingComponent, action)

  def onMove(action: () => Unit): Slider[JSlider] = {
    super.onMove(swingComponent, action)
    this
  }

}