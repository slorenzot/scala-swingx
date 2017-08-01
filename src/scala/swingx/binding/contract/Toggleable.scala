package scala.swingx.binding.contract

import java.awt.event.{ItemEvent, ItemListener}

/**
  * Created by Soulberto Lorenzo on 7/31/2017.
  */
trait Toggleable[T <: javax.swing.AbstractButton, U] {

  protected def source: T = this.asInstanceOf[T]

  protected def change(source: T, action: () => Unit): U = {
    source.addItemListener(new ItemListener() {
      override def itemStateChanged(e: ItemEvent) = action.apply()
    })
    this.asInstanceOf[U]
  }

}
