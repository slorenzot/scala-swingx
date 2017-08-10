package scala.swingx.binding.contract

import java.awt.Adjustable
import java.awt.event.{AdjustmentEvent, AdjustmentListener}

/**
  * Created by Soulberto Lorenzo on 8/9/2017.
  */
trait Scrollable[T <: javax.swing.JScrollPane] {

  def ANY = 2

  def HORIZONTAL = 0

  def VERTICAL = 1

  def UNIT_INCREMENT = 1

  def UNIT_DECREMENT = 2

  def BLOCK_DECREMENT = 3

  def BLOCK_INCREMENT = 4

  def TRACK = 5

  def onChange(source: T, action: (Int, Int, Int) => Unit, orientation: Int = ANY): Scrollable[T] = {
    val listener = new AdjustmentListener() {
      override def adjustmentValueChanged(e: AdjustmentEvent): Unit = {
        if (e.getValueIsAdjusting) return

        val orient = e.getAdjustable.getOrientation

        if (orient == orientation || orientation == ANY) {
          val adjust = e.getAdjustmentType()

          action.apply(e.getValue, orient, adjust)
        }
      }
    }
    source.getHorizontalScrollBar.addAdjustmentListener(listener)
    source.getVerticalScrollBar.addAdjustmentListener(listener)
    this
  }

  def whileMove(source: T, action: (Int, Int, Int) => Unit, orientation: Int = ANY): Scrollable[T] = {
    val listener = new AdjustmentListener() {
      override def adjustmentValueChanged(e: AdjustmentEvent): Unit = {
        if (!e.getValueIsAdjusting) return

        val orient = e.getAdjustable.getOrientation

        if (orient == orientation || orientation == ANY) {
          val adjust = e.getAdjustmentType()

          action.apply(e.getValue, orient, adjust)
        }
      }
    }
    source.getHorizontalScrollBar.addAdjustmentListener(listener)
    source.getVerticalScrollBar.addAdjustmentListener(listener)
    this
  }

}
