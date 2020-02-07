package com.mein.kraft.core.mapGenerator

import com.badlogic.gdx.graphics.g3d.{Model, ModelInstance}
import com.badlogic.gdx.math.collision.Ray
import com.mein.kraft.core.Shape.Shape
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.bullet.collision.{btBoxShape, btCollisionShape}
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody
import com.mein.kraft.core.Effect.Effects

class GameObject(instance: Model, x: Int, y: Int, z: Int, mass: Float = 0,
                 collisionShape : btCollisionShape = new btBoxShape(new Vector3(1, 1, 1))) extends ModelInstance(instance) {

  var xCoord: Int = x
  var yCoord: Int = y
  var zCoord: Int = z

  var positionVector = new Vector3(xCoord * 2, yCoord * 2, zCoord * 2)
  var isolated: Boolean = false
  var biome: IBiome = _

  var body: btRigidBody = _
  private val localInertia = new Vector3
  var shape: Shape = _
  var motionState = new StandardMotionShape()
  motionState.transform = transform

  var renderDimensions: Vector3 = new Vector3(4, 4, 4)

  val effect : Effects = new Effects

  /**
  ** set local inertia
  **/
  if (mass > 0f) {
      collisionShape.calculateLocalInertia(mass, localInertia)
  }
  else {
      localInertia.set(0, 0, 0)
  }

  body = new btRigidBody(mass, motionState, collisionShape, localInertia)
  body.setMotionState(motionState)

  def this(instance: Model) = this(instance, 0, 0, 0)

  /** @return -1 on no intersection, or when there is an intersection: the squared distance between the center of this
  **  object and the point on the ray closest to this object when there is intersection.
  **/
  def intersects(ray: Ray): Float = {
      if (shape == null){
          -1f
      } else{
          shape.intersects(transform, ray)
      }
  }

  //</editor-fold>

  def dispose(): Unit = {
      body.dispose()
      motionState.dispose()
  }
}

