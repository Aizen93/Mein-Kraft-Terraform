package com.mein.kraft.core.Shape

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.{Intersector, Matrix4}
import com.badlogic.gdx.math.collision.{BoundingBox, Ray}

class Box(override val bounds: BoundingBox) extends BaseShape(bounds) {
  override def isVisible(transform: Matrix4, cam: Camera): Boolean = cam.frustum.boundsInFrustum(transform.getTranslation(position).add(center), dimensions)

  override def intersects(transform: Matrix4, ray: Ray): Float = {
    transform.getTranslation(position).add(center)
    if (Intersector.intersectRayBoundsFast(ray, position, dimensions)) {
      val len = ray.direction.dot(position.x - ray.origin.x, position.y - ray.origin.y, position.z - ray.origin.z)
      return position.dst2(ray.origin.x + ray.direction.x * len, ray.origin.y + ray.direction.y * len, ray.origin.z + ray.direction.z * len)
    }
    -1f
  }
}