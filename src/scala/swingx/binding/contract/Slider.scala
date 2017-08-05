package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait Slider[T <: javax.swing.JSlider] {

  private def source: T = this.asInstanceOf[T]

  protected def move(source: T, action: () => Unit): Slider[T] = {
    source.addChangeListener(new ChangeListener() {
      override def stateChanged(e: ChangeEvent) =
        if (!source.getValueIsAdjusting()) action.apply()
    })
    this
  }

}
