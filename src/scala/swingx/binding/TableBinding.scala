package scala.swingx.binding

import java.awt.event.{KeyEvent, KeyListener}
import javax.swing.event.{ListSelectionEvent, ListSelectionListener, TableModelEvent, TableModelListener}
import javax.swing.table.DefaultTableModel

/**
  * Created by Soulberto Lorenzo on 8/5/2017.
  */
case class TableBinding(swingComponent: javax.swing.JTable) {

  private def source: javax.swing.JTable = swingComponent
  private var model: DefaultTableModel = swingComponent.getModel.asInstanceOf[DefaultTableModel]

  def values(): Array[AnyRef] = {
    var rows = Array.empty

    for {
      row <- swingComponent.getModel.getRowCount
      col <- swingComponent.getModel.getColumnCount

      rows :+ swingComponent.getModel.getValueAt(row, col)
    } yield
  }

  def populate(supplier: () => Array[AnyRef]): TableBinding = {
    for (row <- supplier()) {
      model.addRow()
    }
    this
  }

  def cellChange(action: () => Unit): TableBinding = ???

  def rowChange(action: () => Unit): TableBinding = {
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
