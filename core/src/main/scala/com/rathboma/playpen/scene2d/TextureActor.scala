package com.rathboma.playpen.scene2d
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class TextureActor(texture: Texture) extends Actor {
  val region = new TextureRegion(texture)
  region.flip(false, true)

  override def draw(batch: SpriteBatch, parentAlpha: Float) {
    batch.draw(region, getX(), getY())
  }

}