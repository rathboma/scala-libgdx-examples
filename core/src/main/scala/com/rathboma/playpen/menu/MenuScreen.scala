package com.rathboma.playpen.menu

import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.{Table, TextButton, Skin}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.rathboma.playpen.box2dcharacter.Box2DPlayerScreen
import com.rathboma.playpen.animation.SpriteAnimationScreen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rathboma.playpen.buttons._
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.GL10
import com.badlogic.gdx.InputAdapter
import com.rathboma.playpen.util.Logging
import com.rathboma.playpen.PlaypenGame
import com.rathboma.playpen.scene2d.Scene2DScreen

class MenuScreen(game: PlaypenGame) extends InputAdapter with Screen with Logging {
  Gdx.input.setInputProcessor(this)
  logger.info("menu screen start")
  val batch = new SpriteBatch()
  val renderer = new ShapeRenderer()

  val middle = game.width / 2
  val quarterHeight = game.height / 4

  val box2dButton = new SimpleButton("Box2D Demo", middle, quarterHeight)({
      game.setScreen(new Box2DPlayerScreen(game))
      true
    })
  val animationButton = new SimpleButton("Character Animation",middle, quarterHeight*2)({
      game.setScreen(new SpriteAnimationScreen(game))
      true
    })

  val scene2dButton = new SimpleButton("Scene2D", middle, quarterHeight*3)({
    game.setScreen(new Scene2DScreen(game))
    true
    })

  val buttons = List(box2dButton, animationButton, scene2dButton)

  def render(delta: Float) {

    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); 
    buttons.foreach{button =>
      button.drawShapes(renderer)
    }

    batch.begin()
    buttons.foreach{button => 
      button.draw(batch)
    }
    batch.end()    
  }

  val dimensions: Tuple2[Int, Int] = (game.width, game.height)

  override def touchDown(x: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    val y = game.height - screenY
    logger.info("TOUCH DOWN %d, %d".format(x, y))
    buttons.foreach{button =>
      if (button.includes(x, y)) button.touchDown()
    }
    true
  }

  override def touchUp(x: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    val y = game.height - screenY
    logger.info("TOUCH UP %d, %d".format(x, y))
    buttons.foreach{button =>
      if (button.includes(x, y)) button.touchUp()
    }
    true
  }

  override def keyDown(kc: Int) = {
    logger.info("KEY DOWN: %d".format(kc))
    true
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




}