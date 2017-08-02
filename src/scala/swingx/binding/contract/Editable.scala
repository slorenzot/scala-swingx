package scala.swingx.binding.contract

import java.awt.event.{ActionEvent, ActionListener, KeyEvent, KeyListener}
import javax.swing.event.{CaretEvent, CaretListener}

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait Editable[T <: javax.swing.text.JTextComponent, U] {

  protected def source: T = this.asInstanceOf[T]

  private var text: String = ""

  protected def change(source: T, action: () => Unit): U = {
    source.addKeyListener(new KeyListener() {
      override def keyPressed(e: KeyEvent): Unit = {}

      override def keyTyped(e: KeyEvent): Unit = {}

      override def keyReleased(e: KeyEvent): Unit = {
        if (source.getText.compareTo(text) != 0) action.apply()
        text = source.getText
      }
    })

    this.asInstanceOf[U]
  }

}
