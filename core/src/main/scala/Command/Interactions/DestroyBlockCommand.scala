package com.mein.kraft.core.Command.Interactions

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Array
import com.mein.kraft.core.Command.ICommand
import com.mein.kraft.core.Controler.PhysicsController
import com.mein.kraft.core.mapGenerator.GameObject

class DestroyBlockCommand( instancesObjects: Array[GameObject],
                           world: PhysicsController,
                           objectIsClicked: Int,
                           destroySound: Sound) extends ICommand {

  override def execute(): Unit = {
    val block = instancesObjects.get(objectIsClicked)
    unIsolateNeighboorBlocks(block.xCoord, block.yCoord, block.zCoord, instancesObjects)
    instancesObjects.removeIndex(objectIsClicked)
    world.removeObject(block)
    destroySound.play(1.0f)
  }

  def unIsolateNeighboorBlocks(x: Int, y: Int, z: Int, instances: Array[GameObject]): Unit = {
    for (i <- 0 until instances.size) {
      val block: GameObject = instances.get(i)
      if (block.xCoord >= x - 1 && block.xCoord <= x + 1) {
        if (block.yCoord >= y - 1 && block.yCoord <= y + 1) {
          if (block.zCoord >= z - 1 && block.zCoord <= z + 1) {
            block.isolated = false
          }
        }
      }
    }
  }

}
