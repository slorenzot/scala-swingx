package scala.swingx.binding

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
case class Binding[T<: javax.swing.JComponent](swingComponent: T,
                      action: () => Unit) {
  require(!swingComponent.eq(null), "Component reference shouldnt be null or empty!")

  swingComponent.getClass.toString match {
    case "class javax.swing.JLabel" => {
      val source = swingComponent.asInstanceOf[javax.swing.JLabel]
      val label = new LabelBinding(source)
      label.onClick(action)
    }

    case "class javax.swing.JButton" => {
      val source = swingComponent.asInstanceOf[javax.swing.JButton]
      val button = new ButtonBinding(source)
      button.onClick(action)
    }

    case "class javax.swing.JCheckBoxMenuItem" => {
      val source = swingComponent.asInstanceOf[javax.swing.JCheckBoxMenuItem]
      val checkItem = new MenuCheckItemBinding(source)
      checkItem.onChange(action)
    }

    case "class javax.swing.JRadioButtonMenuItem" |
         "class javax.swing.JMenuItem" => {
      val source = swingComponent.asInstanceOf[javax.swing.JMenuItem]
      val button = new MenuItemBinding(source)
      button.onClick(action)
    }

    case "class javax.swing.JTextField" |
         "class javax.swing.JTextArea" |
         "class javax.swing.JTextPane" |
         "class javax.swing.JFormattedTextField" |
         "class javax.swing.JPasswordField" => {
      val source = swingComponent.asInstanceOf[javax.swing.text.JTextComponent]
      val textfield = new TextBinding(source)
      textfield.onChange(action)
    }

    case "class javax.swing.JList" => {
      val source = swingComponent.asInstanceOf[javax.swing.JList[String]]
      var listBinding = new ListBoxBinding(source)
      listBinding.onChange(action)
    }

    case "class javax.swing.JSlider" => {
      val source = swingComponent.asInstanceOf[javax.swing.JSlider]
      val sliderBinding =  new SliderBinding(source)
      sliderBinding.onMove(action)
    }

    case "class javax.swing.JTable" => {
      val source = swingComponent.asInstanceOf[javax.swing.JTable]
      val tableBinding = new TableBinding(source)
      tableBinding.onChange(action)
    }

    case _ => require(false, s"=> ScalaSwingX No support Component: $swingComponent!")
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

  def of(swingComponent: javax.swing.JTextPane) = new TextBinding(swingComponent)

  def of(swingComponent: javax.swing.JTextArea) = new TextBinding(swingComponent)

  def of(swingComponent: javax.swing.JComboBox[String]) = new ComboBoxBinding(swingComponent)

  def of(swingComponent: javax.swing.JTable) = new TableBinding(swingComponent)

}
