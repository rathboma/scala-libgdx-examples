package com.rathboma.playpen.buttons

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer._
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment
import com.rathboma.playpen.util.Logging

class SimpleButton(text: String, x: Int, y: Int)(func: => Boolean) extends Logging {

  val font = new BitmapFont()
  val bounds = font.getBounds(text)
  val w = bounds.width * 2
  val h = bounds.height * 2

  // evil vars
  var pressed = false
  var down = false


  def touchDown() = {
    down = true
  }

  def touchUp() = {
    down = false
    func
  }

  def includes(xt: Int, yt: Int): Boolean = {

    val result = xt >= x - (w/2) && xt <= x + (w/2) && yt >= y - (h / 2) && yt <= y + (h/2)
    logger.debug("button '%s' includes point? %s".format(text, result.toString))
    result
  }

  def drawShapes(renderer: ShapeRenderer) = {
    renderer.begin(ShapeType.FilledRectangle)
    renderer.setColor(if (down) Color.RED else Color.BLUE)
    renderer.filledRect(x - (w/2), y - (h/2), w, h)
    renderer.end()
  }

  def draw(batch: SpriteBatch) = {
    batch.setColor(Color.WHITE)
    font.draw(batch, text, x - (bounds.width / 2), y + (bounds.height / 2))
  }

}