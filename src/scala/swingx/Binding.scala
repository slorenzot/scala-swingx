package scala.swingx

import java.awt.Component

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding[T](val swingComponent: Component) {

  swingComponent.getClass.toString match {
    case "class javax.swing.JButton" => println("Es un boton")
    case _ => println("No se reconoce el tipo")
  }
  
}

object Binding {

  def bind[T](component: javax.swing.JComponent, f: => {}): Binding[T] = new Binding(component)

}
