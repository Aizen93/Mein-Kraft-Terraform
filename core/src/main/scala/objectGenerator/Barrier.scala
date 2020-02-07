package com.mein.kraft.core.objectGenerator

import com.badlogic.gdx.graphics.g3d.Model
import com.mein.kraft.core.mapGenerator.{ Chunk, GameObject}

class Barrier(length:Int=5,position: GameObject,chunk : Chunk) extends BarrierInterface {


  def creation(): Unit = {

    for (u <- position.xCoord to position.xCoord + length) {

      chunk.generateGlobal(new GameObject(new Model,u, position.yCoord, position.zCoord))
    }

  }
}
