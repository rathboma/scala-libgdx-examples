package com.rathboma.playpen.animation

import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{Texture}
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, Animation, TextureRegion}
import com.badlogic.gdx.math.Vector2

class Player(screen: SpriteAnimationScreen) {

  val position = new Vector2(screen.width / 2f, screen.height / 2f)
  val velocity = new Vector2(0f,0f)
  val COLS = 8
  val ROWS = 4
  val SPEED = 60f
  val TRANSITION_SPEED = 0.085f

  val walkSheet: Texture = SpriteAnimationScreen.characterSheet

  val tmp: Array[Array[TextureRegion]] = TextureRegion.split(walkSheet, walkSheet.getWidth() / COLS, walkSheet.getHeight() / ROWS)

  val downAnimation = new Animation(TRANSITION_SPEED, tmp(0): _*)
  val upAnimation = new Animation(TRANSITION_SPEED, tmp(1): _*)
  val leftAnimation = new Animation(TRANSITION_SPEED, tmp(2): _*)
  val rightAnimation = new Animation(TRANSITION_SPEED, tmp(3): _*)
  
  val spriteBatch = new SpriteBatch()

  val movementMappings = Map(
    Movement.UP -> upAnimation,
    Movement.DOWN -> downAnimation,
    Movement.LEFT -> leftAnimation,
    Movement.RIGHT -> rightAnimation
    )
  var movement = Movement.NONE
  var currentAnimation = downAnimation
  var currentFrame: TextureRegion = null
  var stateTime = 0f

  def update(delta: Float) {
    movement match {
      case Movement.UP => velocity.set(0, SPEED)
      case Movement.DOWN => velocity.set(0, -SPEED)
      case Movement.RIGHT => velocity.set(SPEED, 0)
      case Movement.LEFT => velocity.set(-SPEED, 0)
      case _ => velocity.set(0f,0f)
    }
    position.add(velocity.mul(delta))
  }

  def render() {
    currentAnimation = movementMappings.get(movement).getOrElse(currentAnimation)
    stateTime = if (movement == Movement.NONE) 0 else stateTime + Gdx.graphics.getDeltaTime()
    currentFrame = currentAnimation.getKeyFrame(stateTime, true)
    spriteBatch.begin()
    spriteBatch.draw(currentFrame, position.x, position.y)
    spriteBatch.end()
  }


  def move(int: Int) = {
    movement = int
  }


}