
package scala.swingx.utils

import java.awt.Component
import java.io.File
import java.util.Locale
import javax.swing.JOptionPane

import scala.swingx.Image

/**
  * Created by Soulberto Lorenzo on 7/27/2017.
  */
object SwingUtils {

  private val WARNING_ICON = "/resources/icons/Warning_48px.png"
  private val QUESTION_ICON = "/resources/icons/Help_48px.png"
  private val ERROR_ICON = "/resources/icons/Error_48px.png"
  private val CONFIRM_ICON = QUESTION_ICON

  private val ALERT_BUTTON_TEXT = "OK"
  private val ERROR_BUTTON_TEXT = ALERT_BUTTON_TEXT

  private val YES_BUTTON_TEXT = "Yes"
  private val NO_BUTTON_TEXT = "No"
  private val CANCEL_BUTTON_TEXT = "Cancel"

  private def homeDirectory = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory()

  def withLocale(locale: java.util.Locale = java.util.Locale.US) = {
    Locale.setDefault(locale)
    SwingUtils
  }

  def alert(message: String,
            title: String = "Alert",
            component:
            Component = null): Unit = {
    JOptionPane.showOptionDialog(component, message, title, JOptionPane.WARNING_MESSAGE,
      JOptionPane.PLAIN_MESSAGE, Image.file(WARNING_ICON).toIcon, Array(ALERT_BUTTON_TEXT), ALERT_BUTTON_TEXT)
  }

  def error(message: String,
            title: String = "Error",
            component:
            Component = null): Unit = {
    JOptionPane.showOptionDialog(component, message, title, JOptionPane.ERROR_MESSAGE,
      JOptionPane.PLAIN_MESSAGE, Image.file(ERROR_ICON).toIcon, Array(ERROR_BUTTON_TEXT), ERROR_BUTTON_TEXT)
  }

  def confirm(message: String,
              title: String = "Confirm",
              component: Component = null): Int = {
    JOptionPane.showOptionDialog(component, message, title, JOptionPane.YES_NO_OPTION,
      JOptionPane.PLAIN_MESSAGE, Image.file(CONFIRM_ICON).toIcon, Array(YES_BUTTON_TEXT, NO_BUTTON_TEXT), YES_BUTTON_TEXT)
  }

  def confirmCancel(message: String,
                    title: String = "Confirm",
                    component: Component = null): Int = {
    JOptionPane.showOptionDialog(component, message, title, JOptionPane.YES_NO_CANCEL_OPTION,
      JOptionPane.PLAIN_MESSAGE, Image.file(CONFIRM_ICON).toIcon, Array(YES_BUTTON_TEXT, NO_BUTTON_TEXT, CANCEL_BUTTON_TEXT), YES_BUTTON_TEXT)
  }

  def input(message: String,
            title: String = "Input Value",
            component: Component = null): Option[String] = {
    //    val input = JOptionPane.showInputDialog(component, message, title, JOptionPane.QUESTION_MESSAGE,
    //      Image.file(CONFIRM_ICON).toIcon, null, null);
    val panel = new javax.swing.JPanel
    panel.add(new javax.swing.JLabel(message))
    val textField = new javax.swing.JTextField
    textField.setColumns(10)
    panel.add(textField)

    JOptionPane.showOptionDialog(component, panel, title, JOptionPane.YES_NO_CANCEL_OPTION,
      JOptionPane.QUESTION_MESSAGE, Image.file(CONFIRM_ICON).toIcon, Array(YES_BUTTON_TEXT, CANCEL_BUTTON_TEXT), YES_BUTTON_TEXT);

    Option(textField.getText) match {
      case Some(i) => Option(i.toString)
      case _ => Option.empty
    }
  }

  def pick(message: String,
           values: Array[Object],
           title: String = "Pick one"): Option[(Int, String)] = {
    val icon = Image.file(SwingUtils.CONFIRM_ICON).toIcon
    val option = Option(JOptionPane.showInputDialog(null, message, title,
      JOptionPane.QUESTION_MESSAGE, icon, values, "Three"))

    option match {
      case Some(s) => Option((values.indexOf(s), s.toString))
      case _ => Option.empty
    }
  }

  def custom(parent: Component,
             panel: javax.swing.JPanel,
             title: String,
             option: Int,
             ctype: Int,
             icon: Image,
             strings: Array[Object],
             default: String) = {

    JOptionPane.showOptionDialog(panel, panel, title, JOptionPane.DEFAULT_OPTION,
      JOptionPane.QUESTION_MESSAGE, Image.file(CONFIRM_ICON).toIcon, Array(YES_BUTTON_TEXT, CANCEL_BUTTON_TEXT), YES_BUTTON_TEXT);
  }

  def selectFile(title: String,
                 path: String = homeDirectory.getAbsolutePath,
                 parent: Component,
                 isDirectory: Boolean = false,
                 approveText: String = "Select",
                 cancelText: String = "Cancel"): Option[File] = {
    javax.swing.UIManager.put("FileChooser.approveButtonText", approveText)
    javax.swing.UIManager.put("FileChooser.cancelButtonText", cancelText)

    val dialog = new javax.swing.JFileChooser(path)
    dialog.setLocale(Locale.US)
    dialog.setDialogTitle(title)
    dialog.setMultiSelectionEnabled(false)
    dialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY)
    javax.swing.SwingUtilities.updateComponentTreeUI(dialog)

    val option = dialog.showOpenDialog(parent)

    option match {
      case javax.swing.JFileChooser.APPROVE_OPTION => Option(dialog.getSelectedFile)
      case _ => Option.empty
    }
  }

  def selectFiles(title: String,
                  path: String = homeDirectory.getAbsolutePath,
                  parent: Component,
                  isDirectory: Boolean = false,
                  approveText: String = "Select",
                  cancelText: String = "Cancel"): Option[List[File]] = {
    javax.swing.UIManager.put("FileChooser.approveButtonText", approveText)
    javax.swing.UIManager.put("FileChooser.cancelButtonText", cancelText)

    val dialog = new javax.swing.JFileChooser(path)
    dialog.setLocale(Locale.US)
    dialog.setDialogTitle(title)
    dialog.setMultiSelectionEnabled(true)
    dialog.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY)
    javax.swing.SwingUtilities.updateComponentTreeUI(dialog)

    val option = dialog.showOpenDialog(parent)

    option match {
      case javax.swing.JFileChooser.APPROVE_OPTION => Option(dialog.getSelectedFiles.toList)
      case _ => Option.empty
    }
  }

  def saveFile(title: String,
               path: String = homeDirectory.getAbsolutePath,
               parent: Component,
               approveText: String = "Select",
               cancelText: String = "Cancel",
               filter: () => {}): Option[File] = {
    javax.swing.UIManager.put("FileChooser.approveButtonText", approveText)
    javax.swing.UIManager.put("FileChooser.cancelButtonText", cancelText)

    val dialog = new javax.swing.JFileChooser(path)
    dialog.setLocale(Locale.US)
    dialog.setDialogTitle(title)
    javax.swing.SwingUtilities.updateComponentTreeUI(dialog)

    val option = dialog.showSaveDialog(parent)

    option match {
      case javax.swing.JFileChooser.APPROVE_OPTION => Option(dialog.getSelectedFile)
      case _ => Option.empty
    }
  }

}
