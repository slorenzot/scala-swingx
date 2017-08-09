package scala.swingx.binding.contract.generic

import java.awt.event.{MouseAdapter, MouseEvent}

import scala.util.control.Exception

/**
  * Created by Soulberto Lorenzo on 7/30/2017.
  */
trait Clickable[T <: javax.swing.JComponent, U] {

  private def source: T = this.asInstanceOf[T]

  protected def onClick(source: T, action: () => Unit): U = {
    source.addMouseListener(new MouseAdapter() {
      override def mouseClicked(mouseEvent: MouseEvent): Unit = {
        source.setEnabled(false)
        try action.apply()
        catch {
          case _ => _
        }
        finally {
          source.setEnabled(true)
        }
      }
    })

    this.asInstanceOf[U]
  }

}
