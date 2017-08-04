package scala.swingx

import scala.reflect.io.File
import scala.swingx.binding.{Binding, ButtonBinding, MenuCheckItemBinding}
import scala.swingx.utils.{SwingConstants, SwingUtils}

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {

  val window: swingExample.Frame = new swingExample.Frame()

  def console(t: String) = window.jTextArea2.append(s"$t\n")

  Frame.of(window)
    .icon(SwingConstants.DEFAULT_ICON)
    .title("Main Window")
    //    .bind(component, (source, context) => {})
    .bind(window.jMenuItem1, () => SwingUtils.alert("Hi"))
    .bind(window.jRadioButtonMenuItem1, () => SwingUtils.alert("Hi"))
    .bind(window.jTextArea1, () => console("Cambio"))
    .bind(window.jTextPane1, () => console("Cambio"))
    .bind(window.jPasswordField1, () => console(window.jPasswordField1.getText))
    .bind(window.jFormattedTextField1, () => console("Formatted changed"))
    .bind(window.jLabel1, () => console("click"))
    .bind(window.jList1, () => console("selected"))
    .bind(window.jButton1, () => {
      //      SwingUtils.selectFile("Select a File", parent = window, approveText = "Select a file") match {
      //        case Some(f) => SwingUtils.alert(s"File selected ${f.toString()}")
      //        case _ => println("No file selected!")
      //      }
      //      SwingUtils.selectFiles("Select a File", parent = window, approveText = "Select a file") match {
      //        case Some(files) => SwingUtils.alert(s"File selected ${files.size}")
      //        case _ => println("No file selected!")
      //      }
      val dialog: swingExample.Dialog = new swingExample.Dialog(null, true)

      Dialog.of(dialog)
        .from(window)
        .icon(SwingConstants.DEFAULT_ICON)
        .title("Dialog Window")
        .bind(dialog.jButton1, () => {
          SwingUtils.alert("Hi")

          SwingUtils.error("Error")

          val option = SwingUtils.confirm("Some question") match {
            case SwingConstants.YES => console("yes")
            case SwingConstants.NO => console("no")
            case _ => console("Cancelled by User")
          }
        })
        .bind(dialog.jButton1, () => {

        })
        .bind(dialog.jButton2, () => {
          SwingUtils.confirmCancel("Some question") match {
            case SwingConstants.YES => console("yes")
            case SwingConstants.NO => console("no")
            case SwingConstants.CANCEL => console("cancel")
            case _ =>
          }

          SwingUtils.input("What is your age?", "Input value") match {
            case Some(s) => if (s.isEmpty) SwingUtils.alert(s"Lo siento, no suministro su edad") else SwingUtils.alert(s"Usted tiene ${s}")
            case None => SwingUtils.alert(s"Lo siento, no suministro su edad")
          }
        })
        .prepare(d => {
          println(s"preparing $d...")
          1 / 0
          println(d.jButton1)
          println(d.jButton2)
        })
        .terminate(f => println(s"terminating $f..."))
        .center
        .display
    })
    .confirmClosing()
    //    .confirmClosing(() => SwingUtils.confirm("Confirma que desea salir?", "Confirmar salida", null))
    .prepare(f => {
    println(s"preparing $f...")
    Binding.of(f.jCheckBoxMenuItem1).change(() => SwingUtils.alert("Hi"))
    Binding.of(f.jToggleButton1)
      .change(() => {
        SwingUtils.withLocale().alert("Hi")
      })
      .selected(() => console("Seleccionado"))
      .unselected(() => console("Deseleccionado"))

    Binding.of(f.jTextField1)
      .change(() => console(s"El texto cambio"))
      .select(selected => console(selected))

    Binding.of(f.jComboBox1)
      .change(() => {
        SwingUtils.pick("Pick One!", Array("One", "Two", "Three")) match {
          case Some(s) => console(s._2)
          case None =>
        }
      })

    Binding.of(window.jCheckBoxMenuItem1)
      .change(() => console("Cambio"))

    Binding.of(window.jTextPane1)
      .select(selected => console(selected))
    Binding.of(window.jTextArea1)
      .select(selected => console(selected))

  })
    //    .terminate(f => println(s"terminating $f..."))
    //    .opened(f => println(s"Opened Window $f..."))
    //    .closing(f => println(s"Closing Window $f..."))
    //    .closed(f => println(s"Closed Window $f..."))
    //    .maximize
    .center
    .display
}
