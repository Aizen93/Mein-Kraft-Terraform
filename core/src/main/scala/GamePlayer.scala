package com.mein.kraft.core

import GameMode.GameMode
import com.badlogic.gdx.graphics.{Color, PerspectiveCamera, Texture}
import com.badlogic.gdx.graphics.g3d.{Material, Model}
import Inputs.{CharacterMotorCreative, CharacterMotorSurvival, CharacterSettings, ICharacterMotor, ICharacterMovementInput}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Gdx.input
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.bullet.collision.btBoxShape
import com.badlogic.gdx.utils.Array
import com.mein.kraft.core.Controler.PhysicsController
import com.mein.kraft.core.Inputs.Listeners.PlayerListeners.{ActionListener, MovementInput}
import com.mein.kraft.core.ObserverAbstract.Observer
import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.mapGenerator.Block.TextureBuilder
import com.mein.kraft.core.mapGenerator.GameObject

class GamePlayer(gameMode: GameMode) extends GameCharacter with Observer {

  val config = new Configurator("main.properties")
  var camera = createCamera()
  var characterObject: GameObject = createPlayerBody()
  var model: Model = _

  val textureBuilder: TextureBuilder = new TextureBuilder
  var actionListener: ActionListener = _

  var characterMovementInput: ICharacterMovementInput = new MovementInput()
  var characterSettings: CharacterSettings = new CharacterSettings
  var characterMotor: ICharacterMotor = new CharacterMotorSurvival(characterMovementInput, characterSettings, camera, characterObject)

  subject = this.gameMode
  subject.attach(this)

  override def update(): Unit = {
    if(subject.getState()==0) {
      characterSettings.setSurvival
      characterMotor = new CharacterMotorSurvival(characterMovementInput, characterSettings, camera, characterObject)
    }
    if(subject.getState()==1) {
      characterSettings.setCreative
      characterMotor = new CharacterMotorCreative(characterMovementInput, characterSettings, camera, characterObject)
    }
  }

  override def updateMovements(): Unit = {
    characterMovementInput.readMovementInput
    characterMotor.move
  }

  //<editor-fold="initialisation">

  def setSpawn(x: Int, y: Int, z: Int): Unit = {
    characterObject.motionState.transform = characterObject.transform.translate(new Vector3(x, y, z))
  }

  def createCamera(): PerspectiveCamera = {
    input.setCursorCatched(true)
    camera = new PerspectiveCamera(config.getConfig("camera_fieldOfView").toInt, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
    camera.position.set(0f, 0f, 0f)
    camera.lookAt(0f, 0f, 0f)
    camera.near = config.getConfig("camera_near").toFloat
    camera.far = config.getConfig("camera_far").toFloat
    camera.update()
    camera
  }

  def createPlayerBody(): GameObject = {
    val modelBuilder = new ModelBuilder()
    model = modelBuilder.createBox(0f, 0, 0f, new Material(ColorAttribute.createDiffuse(Color.WHITE)), Usage.Position | Usage.Normal)
    characterObject = new GameObject(model, 30, 35, 30, 40, new btBoxShape(new Vector3(0.7f,2,0.7f)))
    characterObject.body.setActivationState(4)
    characterObject.body.setAngularFactor(new Vector3(0, 0, 0))
    characterObject.body.setFriction(3f)
    characterObject
  }

  def initActionListener(gamePlayer: GamePlayer, instancesObjects: Array[GameObject], aimingCross: Texture, world: PhysicsController, gameMode: GameMode): Unit = {
    actionListener = new ActionListener(gamePlayer, instancesObjects, aimingCross, world, textureBuilder, gameMode)
  }

  def findNeighboorVoidBlock(instances : Array[GameObject]) : Boolean = {
    val x = (camera.position.x / 2).toInt
    val y = (camera.position.y / 2).toInt
    val z = (camera.position.z / 2).toInt
    for (i <- 0 until instances.size) {
      val block: GameObject = instances.get(i)
      if (block.xCoord >= x - 4 && block.xCoord <= x + 4) {
        if (block.yCoord >= z - 4 && block.yCoord <= z + 4) {
          if (block.zCoord >= y - 4 && block.zCoord <= y + 4) {
            if(block.effect.teleportEffect){
              return true
            }
          }
        }
      }
    }
    return false
  }

  //</editor-fold>
}
