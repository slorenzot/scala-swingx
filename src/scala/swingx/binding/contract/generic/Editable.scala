package scala.swingx.binding.contract.generic

import java.awt.event.{KeyEvent, KeyListener}

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait Editable[T <: javax.swing.text.JTextComponent, U] {

  private def source: T = this.asInstanceOf[T]

  private var text = ""

  protected def onChange(source: T, action: String => Unit): U = {
    source.addKeyListener(new KeyListener() {
      override def keyPressed(e: KeyEvent): Unit = {}

      override def keyTyped(e: KeyEvent): Unit = {}

      override def keyReleased(e: KeyEvent): Unit = {
        if (source.getText.compareTo(text) != 0) action.apply(source.getText)
        text = source.getText
      }
    })

    this.asInstanceOf[U]
  }

}
