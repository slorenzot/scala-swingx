package scala.swingx.binding

import java.awt.Component
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding[T](swingComponent: T,
                      defaultAction: () => {}) {

  swingComponent.getClass.toString match {
    case "class javax.swing.JButton" => {
      println("XXX")
      swingComponent
        .asInstanceOf[JButton]
        .addActionListener(new ActionListener {
          override def actionPerformed(event: ActionEvent) = defaultAction.apply()
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
