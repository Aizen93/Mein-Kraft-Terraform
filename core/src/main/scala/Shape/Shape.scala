package com.mein.kraft.core.Shape

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.collision.Ray

trait Shape {
  def isVisible(transform: Matrix4, cam: Camera): Boolean

  /** @return -1 on no intersection, or when there is an intersection: the squared distance between the center of this
  *  object and the point on the ray closest to this object when there is intersection.
  **/
  def intersects(transform: Matrix4, ray: Ray): Float
}