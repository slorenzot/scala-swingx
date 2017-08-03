package scala.swingx.binding.contract

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait ListBox[T <: javax.swing.JList[String], U] {

  private def source: T = this.asInstanceOf[T]

  protected def change(source: javax.swing.JList[String], action: () => Unit): U = {
    source.addListSelectionListener(new ListSelectionListener {
      override def valueChanged(event: ListSelectionEvent): Unit =
        if (!event.getValueIsAdjusting) action.apply()
    })
    this.asInstanceOf[U]
  }

}
