package scala.swingx.binding

import javax.swing.JTextField
import javax.swing.text.JTextComponent

import scala.swingx.binding.contract.{Editable, TextSelectable}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class TextBinding(swingComponent: javax.swing.text.JTextComponent)
  extends Editable[javax.swing.text.JTextComponent, TextBinding]
    with TextSelectable[javax.swing.text.JTextComponent, TextBinding] {

  //  def caret(action: () => Unit): TextBinding = super.caret(swingComponent, action)

  def select(action: (String) => Unit): TextBinding = {
    super.select(swingComponent, action)
    this
  }

  def unselect(action: () => Unit): TextBinding = {
    super.unselect(swingComponent, action)
    this
  }

  def change(action: () => Unit): TextBinding = {
    super.change(swingComponent, action)
    this
  }

}
