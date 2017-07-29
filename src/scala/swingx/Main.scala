package scala.swingx

import java.awt.event.ActionEvent

import scala.swingx.binding.ButtonBinding

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
      println("Button 1 click")
    })
//    .maximize
    .display

  val dialog: swingexamples.Dialog = new swingexamples.Dialog(null, true)

  Dialog.of(dialog)
    .from(window)
    .icon(icon)
    .title("Dialog Window")
    .bind(dialog.jButton1, () => {
      Utils.alert("Hi")
      Utils.error("Error")
      val option = Utils.confirm("Some question") match {
        case OptionDialog.YES => println("yes")
        case OptionDialog.NO => println("no")
        case _ => println("Cancelled by User")
      }
    })
    .bind(dialog.jButton2, () => {
      Utils.confirmCancel("Some question") match {
        case OptionDialog.YES => println("yes")
        case OptionDialog.NO => println("no")
        case OptionDialog.CANCEL => println("cancel")
        case _ =>
      }

      Utils.input("What is your age?", "Input value") match {
        case Some(s) => if (s.isEmpty) Utils.alert(s"Lo siento, no suministro su edad") else Utils.alert(s"Usted tiene ${s}")
        case None => Utils.alert(s"Lo siento, no suministro su edad")
      }
    })
    .center
    .display

}
