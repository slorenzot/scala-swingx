package scala.swingx

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main extends App {

  Frame.of(new swingexamples.Frame)
    .icon(Image.file("/resources/icons/default.png").toIcon)
    .title("ventana de prueba")
    .maximize
    .display

  Dialog.of(new swingexamples.Dialog(null, true))
//      .from(window)
    .icon(Image.file("/resources/icons/default.png").toIcon)
    .title("Dialog de prueba")
    .center()
    .display

}
