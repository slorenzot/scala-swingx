package scala.swingx.binding.contract

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}

/**
  * Created by Soulberto Lorenzo on 8/1/2017.
  */
trait ListBox[T, U] {

  private def source: T = this.asInstanceOf[T]

  protected def change(source: T, action: () => Unit): U = {
    source.asInstanceOf[javax.swing.JList[String]].addListSelectionListener(new ListSelectionListener {
      override def valueChanged(event: ListSelectionEvent) = {
        if (!event.getValueIsAdjusting) action.apply()
      }
    })
    this.asInstanceOf[U]
  }

}
