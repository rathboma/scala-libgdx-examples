package com.rathboma.playpen.box2dcharacter


import scalaj.collection.Imports._
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.{Vector3, Matrix4, MathUtils}
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.{World => Box2DWorld}


class Player(world: Box2DWorld) {
  var doJump = false
  val MAX_VELOCITY = 7f

  val box = {
    val bodyDef = new BodyDef()
    bodyDef.`type` = BodyType.DynamicBody
    world.createBody(bodyDef)  
  }
  
  val physicsFixture = {
    val poly = new PolygonShape
    poly.setAsBox(0.45f, 1.4f)
    val physicsFixture = box.createFixture(poly, 1)
    poly.dispose
    physicsFixture
  }
  
  val sensorFixture = {
    val circle = new CircleShape
    circle.setRadius(0.45f)
    circle.setPosition(new Vector2(0, -1.4f))
    val sensorFixture = box.createFixture(circle, 0)
    circle.dispose
    sensorFixture
  }
  box.setBullet(true)

  box.setTransform(10.0f, 4.0f, 0)
  box.setFixedRotation(true)

  var lastGroundTime: Long = 0

  def velocity = box.getLinearVelocity
  def position = box.getPosition

  def limitVelocity() = {
    velocity.x = math.signum(velocity.x) * MAX_VELOCITY
    box.setLinearVelocity(velocity.x, velocity.y)
  }

  def jump(): Unit = {
    box.setLinearVelocity(velocity.x, 0)
    System.out.println("jump before: " + velocity)
    box.setTransform(position.x, position.y + 0.01f, 0)
    box.applyLinearImpulse(0, 30, position.x, position.y)
    System.out.println("jump, " + velocity)
  }

  def moveLeft(): Unit = if (velocity.x > -MAX_VELOCITY) {
      box.applyLinearImpulse(-2f, 0, position.x, position.y)
  }

  def moveRight(): Unit = if (velocity.x < MAX_VELOCITY) {
    box.applyLinearImpulse(2f, 0, position.x, position.y)
  }

  def isGrounded: Boolean = world.getContactList.asScala.exists{contact =>
    if (contact.isTouching() && (
      contact.getFixtureA == sensorFixture ||
      contact.getFixtureB == sensorFixture)) {
      val position = box.getPosition
      val manifold = contact.getWorldManifold
      var below = false
      manifold.getPoints.foreach{point =>
        below = (point.y < position.y - 1.5f) || below
      }
      if (below) true else false
    } else false
  }


}