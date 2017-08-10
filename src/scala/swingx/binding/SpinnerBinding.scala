package scala.swingx.binding

import javax.swing.event.{ChangeEvent, ChangeListener}

/**
  * Created by Soulberto Lorenzo on 8/9/2017.
  */
case class SpinnerBinding(swingComponent: javax.swing.JSpinner) extends Bindable {

  def onChange(action: AnyRef => Unit): SpinnerBinding = {
    swingComponent.addChangeListener(new ChangeListener() {
      override def stateChanged(e: ChangeEvent): Unit = {
        val value = e.getSource.asInstanceOf[javax.swing.JSpinner].getValue
        action.apply(value)
      }
    })
    this
  }

}
