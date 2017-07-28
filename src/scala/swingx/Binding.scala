package scala.swingx

import java.awt.Component
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding[T](val swingComponent: Component,
                      val defaultAction: () => {}) {

  swingComponent.getClass.toString match {
    case "class javax.swing.JButton" => {
      val button = swingComponent.asInstanceOf[JButton]
      button.addActionListener(new ActionListener {
        override def actionPerformed(actionEvent: ActionEvent) = defaultAction.apply()
      })
    }
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

  def bind[T](component: javax.swing.JComponent, action: () => {}): Binding[T] = new Binding(component, action)

}
