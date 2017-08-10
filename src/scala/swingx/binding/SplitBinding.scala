package scala.swingx.binding

import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import javax.swing.JSplitPane

/**
  * Created by Soulberto Lorenzo on 8/9/2017.
  */
case class SplitBinding(swingComponent: JSplitPane) extends Bindable {

  def onResize(action: (Int) => Unit): SplitBinding = {
    swingComponent.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
      override def propertyChange(e: PropertyChangeEvent) = action.apply(e.getNewValue.asInstanceOf[Int])
    })
    this
  }

}
