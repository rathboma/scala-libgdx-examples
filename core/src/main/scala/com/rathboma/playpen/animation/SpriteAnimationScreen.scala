package com.rathboma.playpen.animation

import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{Texture}
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.GL10
import com.rathboma.playpen.util.Logging
import com.rathboma.playpen.PlaypenGame
import com.rathboma.playpen.menu.MenuScreen

object Movement {
  val NONE = -2
  val LEFT = -1
  val RIGHT = 0
  val UP = 1
  val DOWN = 2
}

object SpriteAnimationScreen {
  val characterSheet = new Texture(Gdx.files.internal("assets/character.png"))  
}

class SpriteAnimationScreen(game: PlaypenGame) extends InputAdapter with Screen with Logging {
  logger.info("SpriteAnimationScreen hi!")
  Gdx.input.setInputProcessor(this)
  val width = Gdx.graphics.getWidth
  val height = Gdx.graphics.getHeight

  var movement: Int = Movement.NONE
  val character = new Player(this)

  def render(delta: Float) {
    character.move(movement)
    character.update(delta)

    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); 
    character.render()
  }

  def resize(width: Int, height: Int) {

  }

  def show {

  }

  def hide {

  }

  def pause {

  }

  def resume {

  }

  def dispose {

  }

  // input adapter

  override def keyDown(keycode: Int): Boolean = {

    movement = keycode match {
      case Keys.W => Movement.UP
      case Keys.A => Movement.LEFT
      case Keys.S => Movement.DOWN
      case Keys.D => Movement.RIGHT
      case Keys.ESCAPE => {
        game.setScreen(new MenuScreen(game))
        Movement.NONE
      }
      case _ => Movement.NONE
    }
    false
  }

  override def keyUp(keycode: Int): Boolean = {
    movement = (movement, keycode) match {
      case (Movement.UP, Keys.W) => Movement.NONE
      case (Movement.LEFT, Keys.A) => Movement.NONE
      case (Movement.DOWN, Keys.S) => Movement.NONE
      case (Movement.RIGHT, Keys.D) => Movement.NONE
      case _ => movement
    }
    false
  }


}