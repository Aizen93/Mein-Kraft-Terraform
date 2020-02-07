package com.mein.kraft.core.Inputs.Listeners.PlayerListeners

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.{Gdx, InputAdapter}
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array
import com.mein.kraft.core.Command.CommandInvoker
import com.mein.kraft.core.Command.Interactions.{DestroyBlockCommand, PutBlockCommand}
import com.mein.kraft.core.Controler.PhysicsController
import com.mein.kraft.core.GamePlayer
import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.mapGenerator.Block.TextureBuilder
import com.mein.kraft.core.mapGenerator.GameObject

class CameraInput(gamePlayer: GamePlayer, instancesObjects: Array[GameObject], aimingCross: Texture, world: PhysicsController, texture: TextureBuilder)
  extends InputAdapter{

  val config = new Configurator("main.properties")

  private var dragX = 0
  private var dragY = 0
  private val rotateSpeed = config.getConfig("camera_rotation_speed").toFloat
  private var count = 0

  var buildSound: Sound = Gdx.audio.newSound(Gdx.files.internal(config.getConfig("build")))
  var destroySound: Sound = Gdx.audio.newSound(Gdx.files.internal(config.getConfig("destroy")))

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {
    // rotating on the y axis
    val x = dragX - screenX
    gamePlayer.camera.rotate(Vector3.Y, x * rotateSpeed)
    // rotating on the x and z axis is different
    val y = Math.sin((dragY - screenY).toDouble / 180f).toFloat
    if (Math.abs(gamePlayer.camera.direction.y + y * (rotateSpeed * 5.0f)) < 0.999) {
      gamePlayer.camera.direction.y += y * (rotateSpeed * 5.0f)
    }
    dragX = screenX
    dragY = screenY
    true
  }

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    val objectIsClicked = getObjectClickedOn(aimingCross, instancesObjects)
    if (objectIsClicked != -1) {
      if (button == 0) {
        CommandInvoker.addCommand(new DestroyBlockCommand(instancesObjects, world, objectIsClicked, destroySound))
      }
      if (button == 1) {
        CommandInvoker.addCommand(new PutBlockCommand(aimingCross, instancesObjects, world: PhysicsController, objectIsClicked, texture, buildSound))
      }
    }
    true
  }

  def getObjectClickedOn(aimingCross: Texture, instancesObjects: Array[GameObject]): Int = {
    val ray = gamePlayer.camera.getPickRay((Gdx.graphics.getWidth / 2) - (aimingCross.getWidth / 2), (Gdx.graphics.getHeight / 2) - (aimingCross.getHeight / 2))
    ray.set(gamePlayer.camera.position.x + 1, gamePlayer.camera.position.y + 1, gamePlayer.camera.position.z, gamePlayer.camera.direction.x, gamePlayer.camera.direction.y, gamePlayer.camera.direction.z)
    var result = -1
    var distance = -1f
    var i = 0
    while ( { i < instancesObjects.size }) {
      if (!instancesObjects.get(i).isolated) {
        val dist2 = instancesObjects.get(i).intersects(ray)
        if (dist2 >= 0f && (distance < 0f || dist2 <= distance)) {
          result = i
          distance = dist2
        }
      }
      i += 1; i
    }
    result
  }
}
