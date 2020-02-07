package com.mein.kraft.core.Shape

import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.math.collision.BoundingBox

abstract class BaseShape(val bounds: BoundingBox) extends Shape {
  protected val position = new Vector3
  final val center = new Vector3
  final val dimensions = new Vector3
  bounds.getCenter(center)
  bounds.getDimensions(dimensions)

}