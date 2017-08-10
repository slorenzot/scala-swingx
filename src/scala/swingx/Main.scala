package scala.swingx

import scala.swingx.binding.Binding
import scala.swingx.utils.{SwingConstants, SwingUtils}

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {
  val window: swingExample.Frame = new swingExample.Frame()

  def console(t: String) = window.jTextArea2.append(s"$t\n")

  //  Frame.of(swingExample.Frame)

  Frame.of(window)
    .icon(SwingConstants.DEFAULT_ICON)
    .title("Main Window")
    //    .bind(component, (source, context) => {})
    //    .bind(null, () => {})
    //    .bind(window.jMenuItem1, () => SwingUtils.alert("Hi"))
    //    .bind(window.jRadioButtonMenuItem1, () => SwingUtils.alert("Hi"))
    //    .bind(window.jTextArea1, () => console("TextArea Cambio"))
    //    .bind(window.jTextPane1, () => console("TextPane Cambio"))
    //    .bind(window.jPasswordField1, () => console(window.jPasswordField1.getText))
    //    .bind(window.jFormattedTextField1, () => console("FormattedTextField changed"))
    //    .bind(window.jLabel1, () => console("Label click"))
    //    .bind(window.jList1, () => console("List selected"))
    //    .bind(window.jSlider1, () => console("Slider moved!"))
    //    .bind(window.jButton1, () => {
    //      //      SwingUtils.selectFile("Select a File", parent = window, approveText = "Select a file") match {
    //      //        case Some(f) => SwingUtils.alert(s"File selected ${f.toString()}")
    //      //        case _ => println("No file selected!")
    //      //      }
    //      //      SwingUtils.selectFiles("Select a File", parent = window, approveText = "Select a file") match {
    //      //        case Some(files) => SwingUtils.alert(s"File selected ${files.size}")
    //      //        case _ => println("No file selected!")
    //      //      }
    //      val dialog: swingExample.Dialog = new swingExample.Dialog(null, true)
    //
    //      Dialog.of(dialog)
    //        .from(window)
    //        .icon(SwingConstants.DEFAULT_ICON)
    //        .title("Dialog Window")
    //        .bind(dialog.jButton1, () => {
    //          SwingUtils.alert("Hi")
    //
    //          SwingUtils.error("Error")
    //
    //          val option = SwingUtils.confirm("Some question") match {
    //            case SwingConstants.YES => console("yes")
    //            case SwingConstants.NO => console("no")
    //            case _ => console("Cancelled by User")
    //          }
    //        })
    //        .bind(dialog.jButton1, () => {
    //
    //        })
    //        .bind(dialog.jButton2, () => {
    //          SwingUtils.confirmCancel("Some question") match {
    //            case SwingConstants.YES => console("yes")
    //            case SwingConstants.NO => console("no")
    //            case SwingConstants.CANCEL => console("cancel")
    //            case _ =>
    //          }
    //
    //          SwingUtils.input("What is your age?", "Input value") match {
    //            case Some(s) => if (s.isEmpty) SwingUtils.alert(s"Lo siento, no suministro su edad") else SwingUtils.alert(s"Usted tiene ${s}")
    //            case None => SwingUtils.alert(s"Lo siento, no suministro su edad")
    //          }
    //        })
    //        .prepare(d => {
    //          println(s"preparing $d...")
    //          1 / 0
    //          println(d.jButton1)
    //          println(d.jButton2)
    //        })
    //        .terminate(f => println(s"terminating $f..."))
    //        .center
    //        .display
    //    })
    .confirmClosing()
    //    .confirmClosing(() => SwingUtils.confirm("Confirma que desea salir?", "Confirmar salida", null))
    .prepare(f => {
    println(s"preparing $f...")

    //    Binding.of(window.jSpinner1)

    //    Binding.of(window.jProgressBar1)

    Binding.of(window.jSlider1)
      .onMove(value => println(value))
      .whileMove(value => println(value))

    Binding.of(window.jScrollPane7)
      .onChange((value, orientation, adjustType) => println(s"Scrolled $value px $orientation"))

    Binding.of(window.jButton1)
      .onClick(() => {
        SwingUtils.alert("Click")
        console("Text")
      })

    Binding.of(window.jTable1)
      .clear
      .columns(() => Array("Column1", "Column2"))
      .populate(() => Array(Array("Value1", "Value2")))
      //      .selectRow(() => println("Selected Row"))
      .onChange(() => println(s"Selected"))
      .onFocus(() => println("focused"))
      .onEdit((value, row, col) => println(s"edited $value in $row, $col"))

    Binding.of(f.jCheckBoxMenuItem1).onChange(() => SwingUtils.alert("Hi"))

    Binding.of(f.jToggleButton1)
      .onChange(isSelected => {
        SwingUtils.withLocale().alert("Hi")
      })
      .onSelect(() => console("Seleccionado"))
      .onUnselect(() => console("Deseleccionado"))

    Binding.of(f.jTextField1)
      .onChange(text => console(s"El texto cambio"))
      .onSelect(selected => console(selected))

    Binding.of(f.jComboBox1)
      .onChange(() => {
        SwingUtils.pick("Pick One!", Array("One", "Two", "Three")) match {
          case Some(s) => console(s._2)
          case None =>
        }
      })

    Binding.of(window.jCheckBoxMenuItem1)
      .onChange(() => console("Cambio"))

    Binding.of(window.jTextPane1)
      .onSelect(selected => console(selected))
    Binding.of(window.jTextArea1)
      .onSelect(selected => console(selected))

  })
    //    .terminate(f => println(s"terminating $f..."))
    //    .opened(f => println(s"Opened Window $f..."))
    //    .closing(f => println(s"Closing Window $f..."))
    //    .closed(f => println(s"Closed Window $f..."))
    //    .maximize
    .center
    .display
}
