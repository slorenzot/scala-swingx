package scala.swingx.binding

import java.awt.event.{KeyEvent, KeyListener}
import javax.swing.event.{ListSelectionEvent, ListSelectionListener, TableModelEvent, TableModelListener}

/**
  * Created by Soulberto Lorenzo on 8/5/2017.
  */
case class TableBinding(swingComponent: javax.swing.JTable) {

  private def source: javax.swing.JTable = swingComponent

  def change(action: () => Unit): TableBinding = {
    source.getModel.addTableModelListener(new TableModelListener() {
      override def tableChanged(e: TableModelEvent) = {

      }
    })

    this
  }

  def select(action: () => Unit): TableBinding = {
    source.getSelectionModel.addListSelectionListener(new ListSelectionListener() {
      override def valueChanged(e: ListSelectionEvent) = {}
    })

    this
  }

  def unselect(action: () => Unit): TableBinding = {
    source.getSelectionModel.addListSelectionListener(new ListSelectionListener() {
      override def valueChanged(e: ListSelectionEvent) = {}
    })
    
    this
  }

}
