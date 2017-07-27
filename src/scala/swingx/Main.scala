package scala.swingx

/**
  * Created by Soulberto on 7/27/2017.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val frame = Frame(new swingexamples.Frame)
    frame
      .title("ventana de prueba")
      .display

    val dialog = Dialog(new swingexamples.Dialog(null, true))
    dialog.display
  }

}
