package scala.swingx.binding

import javax.swing.text.JTextComponent

import scala.swingx.binding.contract.{Editable, TextSelectable, Toggleable}

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class TextBinding(swingComponent: javax.swing.text.JTextComponent)
  extends TextSelectable[javax.swing.text.JTextComponent, TextBinding] {

  def caret(action: () => Unit): TextBinding = super.caret(swingComponent, action)

  def select(action: (String) => Unit): TextBinding = super.select(swingComponent, action)

}
