package scala.swingx.binding

import javax.swing.event.{ListSelectionEvent, ListSelectionListener, TableModelEvent, TableModelListener}
import javax.swing.table.DefaultTableModel
import javax.swing.{ListSelectionModel, SwingUtilities}

/**
  * Created by Soulberto Lorenzo on 8/5/2017.
  */
case class TableBinding(swingComponent: javax.swing.JTable) extends Bindable {

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
    source.createDefaultColumnsFromModel()
    updateUI()
    this
  }

  def populate(supplier: () => Array[Array[AnyRef]]): TableBinding = {
    for (row <- supplier()) model.addRow(row)
    updateUI()
    this
  }

  def onChange(action: (Int, Int) => Unit): TableBinding = {
    //    class EditableTableModel extends javax.swing.table.AbstractTableModel {
    //      var columnTitles: Array[String]
    //
    //      var dataEntries: Array[Array[AnyVal]]
    //
    //      var rowCount = dataEntries.size
    //
    //      def EditableTableModel(titles: Array[String], entries: Array[Array[AnyVal]]): Unit = {
    //        this.columnTitles = titles
    //        this.dataEntries = entries
    //      }
    //
    //      def getRowCount(): Int = dataEntries.length
    //
    //      def getColumnCount(): Int = columnTitles.length
    //
    //      def getValueAt(row: Int, column: Int): Array[Array[AnyVal]] = dataEntries(row)(column)
    //
    //      def setValueAt(value: AnyVal, row: Int, column: Int) {
    //        dataEntries(row)(column) = value
    //      }
    //
    //      override def getColumnName(column: Int): String = columnTitles(column)
    //
    //      override def getColumnClass(column: Int): Class[_] = getValueAt(0, column).getClass()
    //
    //      override def isCellEditable(row: Int, column: Int) = true
    //
    //    }
    //    class TableCellEditorModel extends javax.swing.DefaultCellEditor {
    //
    //      def CleanCellEditor make () {
    //        var field = new javax.swing.JTextField()
    //
    //        field.setBorder(null)
    //        System.out.println("make")
    //
    //        return new TableCellEditorModel(field)
    //      }
    //
    //      def CleanCellEditor(textfield: javax.swing.JTextField) = {
    //        super.(textField)
    //      }
    //
    //      def getTableCellEditorComponent(table: javax.swing.JTable,value:AnyVal, isSelected:Boolean, row: Int,column: Int): Component = {
    //        return super.getTableCellEditorComponent(table, null, isSelected, row, column)
    //      }
    //    }
    //    source.setCellEditor(new TableCellEditorModel)
    this
  }

  def onChange(action: () => Unit): TableBinding = {
    source.getModel.addTableModelListener(new TableModelListener() {

      private var selected: Array[Int] = Array.empty

      override def tableChanged(e: TableModelEvent) =
        if (!source.getSelectionModel.getValueIsAdjusting) {
          println(selected.length)
          action.apply()
        }
    })
    this
  }

  def onFocus(action: () => Unit): TableBinding = {
    source.setRowSelectionAllowed(true)
    source.getSelectionModel.addListSelectionListener(new ListSelectionListener() {
      override def valueChanged(e: ListSelectionEvent) = {
        val singleSelection = (source.getSelectionModel == ListSelectionModel.SINGLE_SELECTION)
        if (singleSelection && !source.getSelectionModel.getValueIsAdjusting) action.apply()
      }
    })
    this
  }

  def onFocus(action: (Int, Int) => Unit): TableBinding = {
    source.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(e: java.awt.event.MouseEvent) {
        val row = source.rowAtPoint(e.getPoint())
        val col = source.columnAtPoint(e.getPoint())
        action.apply(row, col)
      }
    })
    this
  }

  def onEdit(action: (String, Int, Int) => Unit): TableBinding = {
    //    source.setColumnSelectionAllowed(false)
    source.setCellSelectionEnabled(true)
    source.getModel.addTableModelListener(new TableModelListener {
      override def tableChanged(tableModelEvent: TableModelEvent) = {

      }
    })
    this
  }

  def onBlur(action: (String, Int, Int) => {}): TableBinding = ???

  def onUnselect(action: () => Unit): TableBinding = {
    source.getSelectionModel.addListSelectionListener(new ListSelectionListener() {
      override def valueChanged(e: ListSelectionEvent) = {
        if (!swingComponent.getSelectionModel.getValueIsAdjusting) println("Unselected")
      }
    })
    this
  }

}
