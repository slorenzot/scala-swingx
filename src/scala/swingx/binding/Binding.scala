package scala.swingx.binding

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding[T](swingComponent: T,
                      action: () => Unit) {

  swingComponent.getClass.toString match {
    case "class javax.swing.JLabel" => {
      val source = swingComponent.asInstanceOf[javax.swing.JLabel]
      val label = new LabelBinding(source)
      label.click(action)
    }

    case "class javax.swing.JButton" => {
      val source = swingComponent.asInstanceOf[javax.swing.JButton]
      val button = new ButtonBinding(source)
      button.click(action)
    }

    case "class javax.swing.JCheckBoxMenuItem" => {
      val source = swingComponent.asInstanceOf[javax.swing.JCheckBoxMenuItem]
      val checkItem = new MenuCheckItemBinding(source)
      checkItem.change(action)
    }

    case "class javax.swing.JRadioButtonMenuItem" |
         "class javax.swing.JMenuItem" => {
      val source = swingComponent.asInstanceOf[javax.swing.JMenuItem]
      val button = new MenuItemBinding(source)
      button.click(action)
    }

    case "class javax.swing.JTextField" |
         "class javax.swing.JTextArea" |
         "class javax.swing.JTextPane" |
         "class javax.swing.JFormattedTextField" |
         "class javax.swing.JPasswordField" => {
      val source = swingComponent.asInstanceOf[javax.swing.text.JTextComponent]
      val textfield = new TextBinding(source)
      textfield.change(action)
    }

    case "class javax.swing.JList" => {
      val source = swingComponent.asInstanceOf[javax.swing.JList[String]]
      var listBinding = new ListBoxBinding(source)
      listBinding.change(action)
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

  def of(swingComponent: javax.swing.JButton) = new ButtonBinding(swingComponent)

  def of(swingComponent: javax.swing.JToggleButton) = new ToggleButtonBinding(swingComponent)

  def of(swingComponent: javax.swing.JMenuItem) = new MenuItemBinding(swingComponent)

  def of(swingComponent: javax.swing.JCheckBoxMenuItem) = new MenuCheckItemBinding(swingComponent)

  def of(swingComponent: javax.swing.JRadioButtonMenuItem) = new MenuRadioItemBinding(swingComponent)

  def of(swingComponent: javax.swing.JTextField) = new TextBinding(swingComponent)

  def of(swingComponent: javax.swing.JComboBox[String]) = new ComboBoxBinding(swingComponent)

}
