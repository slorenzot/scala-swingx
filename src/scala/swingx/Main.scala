package scala.swingx

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {
  val icon = Image.file("/resources/icons/default.png").toIcon

  val event = () => println("click en button")

  Frame.of(new swingexamples.Frame)
    .icon(icon)
    .title("Ventana de prueba")
    .maximize
    .display

  val dialog: swingexamples.Dialog = new swingexamples.Dialog(null, true)
  Dialog.of(dialog)
    //      .from(window)
    .icon(icon)
    .title("Dialog de prueba")
    .center()
    .bind(dialog.jButton1, event)
    .bind(dialog.jButton2, event)
    .display

  Utils.alert("Hola")

}
