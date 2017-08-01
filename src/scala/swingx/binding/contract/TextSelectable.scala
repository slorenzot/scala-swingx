package scala.swingx.binding.contract

import javax.swing.event.{CaretEvent, CaretListener}

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait TextSelectable[T <: javax.swing.text.JTextComponent, U] extends Editable[T, U] {

  protected def select(source: T, action: (String) => Unit): U = {
    source.addCaretListener(new CaretListener() {
      override def caretUpdate(e: CaretEvent) = {
        val selection = source.getText.substring(Math.min(e.getDot, e.getMark), Math.max(e.getDot, e.getMark))
        action.apply(selection)
      }
    })

    this.asInstanceOf[U]
  }

}
