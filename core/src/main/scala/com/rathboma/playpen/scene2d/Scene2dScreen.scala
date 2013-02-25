package com.rathboma.playpen.scene2d

import com.badlogic.gdx.graphics.OrthographicCamera
import com.rathboma.playpen.PlaypenGame
import com.badlogic.gdx.scenes.scene2d.{Stage, Group}
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.GL10
import com.badlogic.gdx.{InputAdapter, Gdx, Screen}


class Scene2DScreen(game: PlaypenGame) extends InputAdapter with Screen {

  val camera = new OrthographicCamera()
  camera.setToOrtho(true)

  val stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true)
  stage.setCamera(camera)
  Gdx.input.setInputProcessor(stage)
  val character = new TextureActor(new Texture("assets/star.png"))
  val group = new Group()
  group.addActor(character)
  group.setOrigin(96, 96)
  group.setPosition(600,100)
  stage.addActor(group)

  val moveAction = Actions.moveTo(600, 400, 5)
  val moveAction2 = Actions.moveTo(100, 100, 5)
  val rotateAction = Actions.repeat(-1, Actions.rotateBy(360, 2))
  val together = Actions.parallel(Actions.sequence(moveAction, moveAction2), rotateAction)
  group.addAction(together)

  def render(delta: Float) {
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT)
    camera.update()
    stage.act(Gdx.graphics.getDeltaTime())
    stage.draw()
  }

  def resize(width: Int, height: Int) {
    stage.setViewport(width, height, true);
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