package scala.swingx.binding.contract.generic

import java.awt.event.{KeyAdapter, KeyEvent}

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait Editable[T <: javax.swing.text.JTextComponent, U] {

  private def source: T = this.asInstanceOf[T]

  private var text = ""
  private var t0: Long = 0

  protected def whileTyping(source: T, action: String => Unit): U = {
    source.addKeyListener(new KeyAdapter() {
      override def keyReleased(e: KeyEvent): Unit = {
        text = source.getText

        if (System.currentTimeMillis() - t0 <= 1000) {
          action.apply(text)
        }

        t0 = System.currentTimeMillis() // actualiza el tiempo de la ultima presion de tecla
      }
    })

    this.asInstanceOf[U]
  }

  protected def onChange(source: T, action: String => Unit): U = {
    source.addKeyListener(new KeyAdapter() {
      override def keyReleased(e: KeyEvent): Unit = {
        if (source.getText.compareTo(text) != 0) action.apply(source.getText)
        text = source.getText
      }
    })

    this.asInstanceOf[U]
  }

}
