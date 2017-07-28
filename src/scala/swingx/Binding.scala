package scala.swingx

import java.awt.Component

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding(val swingComponent: Component) {

}

object Binding {

  def bind(component: Component): Binding = new Binding(component)

}
