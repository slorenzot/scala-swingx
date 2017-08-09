package scala.swingx.binding

import java.awt.Adjustable
import java.awt.event.{AdjustmentEvent, AdjustmentListener}
import javax.swing.JScrollPane

/**
  * Created by Soulberto on 8/9/2017.
  */
case class ScrollBinding(swingComponent: JScrollPane) {
  private val source: JScrollPane = swingComponent

  def onHChange(action: () => Unit): ScrollBinding = {
    source.getHorizontalScrollBar.addAdjustmentListener(new AdjustmentListener() {
      override def adjustmentValueChanged(e: AdjustmentEvent): Unit = {
        println("Moved!")
        if (e.getValueIsAdjusting) return

        val orient = e.getAdjustable.getOrientation

        if (orient == Adjustable.HORIZONTAL) {
          val adjust = e.getAdjustmentType()
          adjust match {
            case AdjustmentEvent.UNIT_INCREMENT => println("Scrollbar was increased by one unit")

            case AdjustmentEvent.UNIT_DECREMENT => println("Scrollbar was decreased by one unit")

            case AdjustmentEvent.BLOCK_INCREMENT => println("Scrollbar was increased by one block")

            case AdjustmentEvent.BLOCK_DECREMENT => println("Scrollbar was decreased by one block")

            case AdjustmentEvent.TRACK => println("The knob on the scrollbar was dragged")
          }

          val value = e.getValue
        }
      }
    })
    this
  }

  def onVChange(action: () => Unit): ScrollBinding = {
    source.getHorizontalScrollBar.addAdjustmentListener(new AdjustmentListener() {
      override def adjustmentValueChanged(e: AdjustmentEvent): Unit = {
        println("Moved!")
        if (e.getValueIsAdjusting) return

        val orient = e.getAdjustable.getOrientation

        if (orient == Adjustable.VERTICAL) {
          val adjust = e.getAdjustmentType()
          adjust match {
            case AdjustmentEvent.UNIT_INCREMENT => println("Scrollbar was increased by one unit")

            case AdjustmentEvent.UNIT_DECREMENT => println("Scrollbar was decreased by one unit")

            case AdjustmentEvent.BLOCK_INCREMENT => println("Scrollbar was increased by one block")

            case AdjustmentEvent.BLOCK_DECREMENT => println("Scrollbar was decreased by one block")

            case AdjustmentEvent.TRACK => println("The knob on the scrollbar was dragged")
          }

          val value = e.getValue
        }
      }
    })
    this
  }

}
