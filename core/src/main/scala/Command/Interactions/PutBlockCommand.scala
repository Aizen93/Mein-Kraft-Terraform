package com.mein.kraft.core.Command.Interactions

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.collision.BoundingBox
import com.badlogic.gdx.utils.Array
import com.mein.kraft.core.Command.ICommand
import com.mein.kraft.core.Controler.PhysicsController
import com.mein.kraft.core.Shape.{Box, Shape}
import com.mein.kraft.core.mapGenerator.Block.TextureBuilder
import com.mein.kraft.core.mapGenerator.GameObject

class PutBlockCommand(aimingCross: Texture, instancesObjects: Array[GameObject], world: PhysicsController, objectIsClicked: Int,
                      texture: TextureBuilder, buildsound: Sound) extends ICommand {

  override def execute(): Unit = {
    val block: GameObject = instancesObjects.get(objectIsClicked)
    texture.applyMaterial()
    val instance: GameObject = new GameObject(texture.getblockModel(), block.xCoord, block.yCoord, block.zCoord + 1)
    instance.transform.setToTranslation(instance.positionVector.x, instance.positionVector.z, instance.positionVector.y)
      .rotate(0, 0, 1, 90)

    if(texture.selectedBlockTextInfo.equals("Void")) instance.effect.teleportEffect = true

    buildBoundingBox(instance)
    if (!contains(instancesObjects, instance)) {
      instancesObjects.add(instance)
      buildsound.play(1.0f)
    }
  }

  def contains(instances: Array[GameObject], obj: GameObject): Boolean = {
    var block: GameObject = null
    for (i <- 0 until instances.size) {
      block = instances.get(i)
      if (block.xCoord.equals(obj.xCoord) && block.yCoord.equals(obj.yCoord) && block.zCoord.equals(obj.zCoord)) {
        return true
      }
    }
    false
  }

  def buildBoundingBox(instance: GameObject): Unit = {
    val bounds: BoundingBox = new BoundingBox
    instance.calculateBoundingBox(bounds)
    val blockShape: Shape = new Box(bounds)
    instance.shape = blockShape
  }
}
