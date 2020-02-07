package com.mein.kraft.core.mapGenerator.Block

import com.mein.kraft.core.mapGenerator.GameObject

class BlockBuilder {
  def placeBlock(block: GameObject): Unit = {
    block.transform.setToTranslation(block.positionVector.x, block.positionVector.z, block.positionVector.y)
      .rotate(0, 0, 1, 90)
  }
}
