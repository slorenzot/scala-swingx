package scala.swingx

import scala.swingx.utils.{SwingConstants, SwingUtils}

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {
  val icon = Image.file("/resources/icons/default.png").toIcon

  val window: swingexamples.Frame = new swingexamples.Frame()

  Frame.of(window)
    .icon(icon)
    .title("Main Window")
    .bind(window.jButton1, () => {
      SwingUtils.alert("Button 1 click!")
    })
    .confirmClosing()
    //    .confirmClosing(() => SwingUtils.confirm("Confirma que desea salir?", "Confirmar salida", null))
    .prepare((frame: javax.swing.JFrame) => println(frame))
    .opened(s => println(s"Opened Windows!"))
    .closed(s => println(s"Closed Windows!"))
    //    .maximize
    .center
    .display

  val dialog: swingexamples.Dialog = new swingexamples.Dialog(null, true)

  Dialog.of(dialog)
    .from(window)
    .icon(icon)
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
    .center
    .display

}
