package scala.swingx

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {

  Frame.of(new swingexamples.Frame)
    .icon(Image.file("/resources/icons/default.png").toIcon)
    .title("Ventana de prueba")
    .maximize
    .display

  val dialog: swingexamples.Dialog = new swingexamples.Dialog(null, true)
  Dialog.of(dialog)
    //      .from(window)
    .icon(Image.file("/resources/icons/default.png").toIcon)
    .title("Dialog de prueba")
    .center()
    .bind(dialog.jButton1, () => {})
    .display

}
