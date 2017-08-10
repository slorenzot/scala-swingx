package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait Sliderable[T <: javax.swing.JSlider] {

  protected def onMove(source: T, action: Int => Unit): Sliderable[T] = {
    source.addChangeListener(new ChangeListener() {
      override def stateChanged(e: ChangeEvent) = {
        val value = source.getValue
        if (!source.getValueIsAdjusting()) action.apply(value)
      }
    })
    this
  }

  protected def whileMove(source: T, action: Int => Unit): Sliderable[T] = {
    source.addChangeListener(new ChangeListener() {
      override def stateChanged(e: ChangeEvent) = {
        val value = source.getValue
        if (source.getValueIsAdjusting()) action.apply(value)
      }
    })
    this
  }

}
