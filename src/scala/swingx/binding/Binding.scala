package scala.swingx.binding

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding[T](swingComponent: T,
                      action: () => Unit) {

  swingComponent.getClass.toString match {
    case "class javax.swing.JButton" => {
      val button = new ButtonBinding(swingComponent.asInstanceOf[javax.swing.JButton])
      button.click(action)
    }

    case "class javax.swing.JRadioButtonMenuItem" |
         "class javax.swing.JCheckBoxMenuItem" |
         "class javax.swing.JMenuItem" => {
      val button = new MenuItemBinding(swingComponent.asInstanceOf[javax.swing.JMenuItem])
      button.click(action)
    }
    case _ => println(s"=> ScalaSwingX No support Component: $swingComponent!")
  }

}

object Binding {

  def of[T <: javax.swing.JComponent](swingComponent: T) = {
    new Binding[T](swingComponent, () => Unit)
  }

  def bind[U <: javax.swing.JComponent](component: U, action: () => Unit): U = {
    new Binding[U](component, action)
    component
  }

}
