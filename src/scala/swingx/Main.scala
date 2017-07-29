package scala.swingx

import java.awt.event.ActionEvent

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {
  val icon = Image.file("/resources/icons/default.png").toIcon

  //  Frame.of(new swingexamples.Frame)
  //    .icon(icon)
  //    .title("Ventana de prueba")
  //    .maximize
  //    .display

  val dialog: swingexamples.Dialog = new swingexamples.Dialog(null, true)

  ButtonBinding(dialog.jButton1).click(binding => {
    println("hola")
    this
  })

  Dialog.of(dialog)
    //      .from(window)
    .icon(icon)
    .title("Dialog de prueba")
    .center()
    //    .bind(dialog.jButton1, () => println("click en button"))
    //    .bind(dialog.jButton2, () => println("click en button"))
    .display


  Utils.alert("Hola")
  Utils.error("Error")
  Utils.confirm("Pregunta") match {
    case OptionDialog.YES => println("yes")
    case OptionDialog.NO => println("no")
    case _ =>
  }
  Utils.confirmCancel("Pregunta") match {
    case OptionDialog.YES => println("yes")
    case OptionDialog.NO => println("no")
    case OptionDialog.CANCEL => println("cancel")
    case _ =>
  }

  Utils.input("Indique su edad", "Ingrese una valor") match {
    case Some(s) => if (s.isEmpty) Utils.alert(s"Lo siento, no suministro su edad") else Utils.alert(s"Usted tiene ${s}")
    case None => Utils.alert(s"Lo siento, no suministro su edad")
  }

}
