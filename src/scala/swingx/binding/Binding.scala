package scala.swingx.binding

import java.awt.Component
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding[T](swingComponent: T,
                      action: () => Unit) {

  swingComponent.getClass.toString match {
    case "class javax.swing.JButton" => {
      val source = swingComponent.asInstanceOf[javax.swing.JButton]
      source
        .addActionListener(new ActionListener {
          override def actionPerformed(event: ActionEvent) = try action.apply() catch {
            case e: Exception => println(e)
          }
        })
    }
    case _ => println(s"Not supported Component!")
  }

}

object Binding {

  def of[T](swingComponent: T) = {
    new Binding[T](swingComponent, () => Unit)
  }

}
