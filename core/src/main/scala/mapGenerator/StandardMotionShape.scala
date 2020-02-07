package com.mein.kraft.core.mapGenerator

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState

class StandardMotionShape extends  btMotionState{
  var transform: Matrix4 = _

  override def getWorldTransform(worldTrans: Matrix4): Unit = {
    worldTrans.set(transform)
  }

  override def setWorldTransform(worldTrans: Matrix4): Unit = {
    transform.set(worldTrans)
  }

}
