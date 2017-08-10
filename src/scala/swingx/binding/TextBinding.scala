package scala.swingx.binding

import javax.swing.JTextField
import javax.swing.text.JTextComponent

import scala.swingx.binding.contract.TextSelectable
import scala.swingx.binding.contract.generic.Editable

/**
  * Created by Soulberto Lorenzo on 7/28/2017.
  */
case class TextBinding(swingComponent: javax.swing.text.JTextComponent)
  extends Editable[javax.swing.text.JTextComponent, TextBinding]
    with TextSelectable[javax.swing.text.JTextComponent, TextBinding] {

  //  def caret(action: () => Unit): TextBinding = super.caret(swingComponent, action)

  def onSelect(action: (String) => Unit): TextBinding = {
    super.onSelect(swingComponent, action)
    this
  }

  def onUnselect(action: () => Unit): TextBinding = {
    super.onUnselect(swingComponent, action)
    this
  }

  def onChange(action: String => Unit): TextBinding = {
    super.onChange(swingComponent, action)
    this
  }

  def whileTyping(action: String => Unit): TextBinding = {
    super.whileTyping(swingComponent, action)
    this
  }

}