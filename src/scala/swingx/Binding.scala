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

  def click(e: => {}) = {}

  def change(e: => {}) = {}

  def enter(e: => {}) = {}

  def over(e: => {}) = {}

  def out(e: => {}) = {}

  def select(e: => {}) = {}

  def unselect(e: => {}) = {}

}

object Binding {

  def bind[T](component: javax.swing.JComponent, f: => {}): Binding[T] = new Binding(component)

}
