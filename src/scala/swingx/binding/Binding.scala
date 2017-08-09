package scala.swingx.binding

import javax.swing.JScrollPane

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
object Binding {
  def of(swingComponent: JScrollPane) = new ScrollBinding(swingComponent)


  def of(swingComponent: javax.swing.JButton) = new ButtonBinding(swingComponent)

  def of(swingComponent: javax.swing.JLabel) = new LabelBinding(swingComponent)

  def of(swingComponent: javax.swing.JToggleButton) = new ToggleButtonBinding(swingComponent)

  def of(swingComponent: javax.swing.JMenuItem) = new MenuItemBinding(swingComponent)

  def of(swingComponent: javax.swing.JCheckBoxMenuItem) = new MenuCheckItemBinding(swingComponent)

  def of(swingComponent: javax.swing.JRadioButtonMenuItem) = new MenuRadioItemBinding(swingComponent)

  def of(swingComponent: javax.swing.JTextField) = new TextBinding(swingComponent)

  def of(swingComponent: javax.swing.JTextPane) = new TextBinding(swingComponent)

  def of(swingComponent: javax.swing.JTextArea) = new TextBinding(swingComponent)

  def of(swingComponent: javax.swing.JComboBox[String]) = new ComboBoxBinding(swingComponent)

  def of(swingComponent: javax.swing.JTable) = new TableBinding(swingComponent)

  def of(swingComponent: javax.swing.JSlider) = new SliderBinding(swingComponent)

}
