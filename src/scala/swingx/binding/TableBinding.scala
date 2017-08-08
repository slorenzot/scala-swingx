package scala.swingx.binding

import java.awt.event.{KeyEvent, KeyListener}
import javax.swing.SwingUtilities
import javax.swing.event.{ListSelectionEvent, ListSelectionListener, TableModelEvent, TableModelListener}
import javax.swing.table.DefaultTableModel

/**
  * Created by Soulberto Lorenzo on 8/5/2017.
  */
case class TableBinding(swingComponent: javax.swing.JTable) {

  private def source: javax.swing.JTable = swingComponent

  private var model: DefaultTableModel = swingComponent.getModel.asInstanceOf[DefaultTableModel]

  private def updateUI(): Unit = SwingUtilities.invokeLater(new Runnable {
    override def run() = swingComponent.updateUI()
  })

  //  def values(): Array[AnyRef] = {
  //    var rows = Array.empty
  //
  //    for {
  //      row <- swingComponent.getModel.getRowCount
  //      col <- swingComponent.getModel.getColumnCount
  //
  //      rows :+ swingComponent.getModel.getValueAt(row, col)
  //    } yield rows
  //  }

  def clear(): TableBinding = {
    model = new DefaultTableModel
    swingComponent.setModel(model)
    updateUI()
    this
  }

  def columns(supplier: () => Array[String]): TableBinding = {
    for (col <- supplier()) model.addColumn(col)
    updateUI()
    this
  }

  def populate(supplier: () => Array[Array[AnyRef]]): TableBinding = {
    for (row <- supplier()) model.addRow(row)
    updateUI()
    this
  }

  def cellChange(action: () => Unit): TableBinding = ???

  def rowChange(action: () => Unit): TableBinding = {
    source.getModel.addTableModelListener(new TableModelListener() {
      override def tableChanged(e: TableModelEvent) = {
        if (!swingComponent.getSelectionModel.getValueIsAdjusting) println("Table Changed")
      }
    })
    this
  }

  def selectRow(action: () => Unit): TableBinding = {
    source.getSelectionModel.addListSelectionListener(new ListSelectionListener() {
      override def valueChanged(e: ListSelectionEvent) = {
        if (!swingComponent.getSelectionModel.getValueIsAdjusting) println("Row Selected")
      }
    })
    this
  }

  def selectCell(action: () => Unit): TableBinding = {
    source.getSelectionModel.addListSelectionListener(new ListSelectionListener() {
      override def valueChanged(e: ListSelectionEvent) = {
        if (!swingComponent.getSelectionModel.getValueIsAdjusting) println("Cell Selected")
      }
    })
    this
  }

  def unselect(action: () => Unit): TableBinding = {
    source.getSelectionModel.addListSelectionListener(new ListSelectionListener() {
      override def valueChanged(e: ListSelectionEvent) = {
        if (!swingComponent.getSelectionModel.getValueIsAdjusting) println("Unselected")
      }
    })
    this
  }

}
