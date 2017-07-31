package scala.swingx.binding.contract

/**
  * Created by Soulberto Lorenzo on 7/31/2017.
  */
trait Toggleable[T <: javax.swing.JToggleButton, U] {

  def source: T = this.asInstanceOf[T]

  def change(action: T => Unit)

}
