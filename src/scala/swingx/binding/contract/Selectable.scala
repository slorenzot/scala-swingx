package scala.swingx.binding.contract

import java.awt.ItemSelectable

/**
  * Created by Soulberto Lorenzo on 7/31/2017.
  */
trait Selectable[T <: ItemSelectable, U] {

  def source: T = this.asInstanceOf[T]

  def select(action: T => Unit): U

}
