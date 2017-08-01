package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.event.{CaretEvent, CaretListener}

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait Editable[T <: javax.swing.text.JTextComponent, U] {

  protected def source: T = this.asInstanceOf[T]

  protected def caret(source: T, action: () => Unit): U = {
    source.addCaretListener(new CaretListener() {
      override def caretUpdate(e: CaretEvent) = action.apply()
    })

    this.asInstanceOf[U]
  }

}
