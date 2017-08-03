package scala.swingx

import scala.swingx.binding.{Binding, ButtonBinding, MenuCheckItemBinding}
import scala.swingx.utils.{SwingConstants, SwingUtils}

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {
  val window: swingExample.Frame = new swingExample.Frame()

  Frame.of(window)
    .icon(SwingConstants.DEFAULT_ICON)
    .title("Main Window")
    //    .bind(component, (source, context) => {})
    .bind(window.jMenuItem1, () => SwingUtils.alert("Hi"))
    .bind(window.jRadioButtonMenuItem1, () => SwingUtils.alert("Hi"))
    .bind(window.jTextArea2, () => SwingUtils.alert("Change"))
    .bind(window.jButton1, () => {
      val dialog: swingExample.Dialog = new swingExample.Dialog(null, true)

      Dialog.of(dialog)
        .from(window)
        .icon(SwingConstants.DEFAULT_ICON)
        .title("Dialog Window")
        .bind(dialog.jButton1, () => {
          SwingUtils.alert("Hi")

          SwingUtils.error("Error")

          val option = SwingUtils.confirm("Some question") match {
            case SwingConstants.YES => println("yes")
            case SwingConstants.NO => println("no")
            case _ => println("Cancelled by User")
          }
        })
        .bind(dialog.jButton1, () => {

        })
        .bind(dialog.jButton2, () => {
          SwingUtils.confirmCancel("Some question") match {
            case SwingConstants.YES => println("yes")
            case SwingConstants.NO => println("no")
            case SwingConstants.CANCEL => println("cancel")
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
    Binding.of(f.jCheckBoxMenuItem1)
      .change(() => SwingUtils.alert("Hi"))
    Binding.of(f.jToggleButton1)
      .change(() => {
        SwingUtils.withLocale().alert("Hi")
      })
      .selected(() => println("Seleccionado"))
      .unselected(() => println("Deseleccionado"))

    Binding.of(f.jTextField1)
      .change(() => println("El texto cambio"))
      .select(selected => println(selected))

    Binding.of(f.jComboBox1)
      .change(() => {
        println("Cambio")
        val selection = SwingUtils.pick("Pick One!", Array("One", "Two", "Three"))
      })

    Binding.of(window.jCheckBoxMenuItem1)
      .change(() => println("Cambio"))
    //    (new MenuCheckItemBinding(window.jCheckBoxMenuItem1))
    //      .change(() => SwingUtils.alert("Hi"))
  })
    //    .terminate(f => println(s"terminating $f..."))
    //    .opened(f => println(s"Opened Window $f..."))
    //    .closing(f => println(s"Closing Window $f..."))
    //    .closed(f => println(s"Closed Window $f..."))
    //    .maximize
    .center
    .display
}
