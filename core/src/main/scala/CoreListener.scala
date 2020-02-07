package com.mein.kraft.core

import java.io.File
import java.util

import Controler.{LightControler, MapController, PhysicsController, PlayerControler, TimeCycleController}
import GameMode.GameMode
import com.badlogic.gdx.Gdx._
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.g3d._
import com.badlogic.gdx.graphics.{Color, GL20, PerspectiveCamera, Texture}
import com.mein.kraft.core.configurator.Configurator
import com.badlogic.gdx.utils.Array
import com.mein.kraft.core.Shape.Box
import com.badlogic.gdx.math.collision.BoundingBox
import mapGenerator.{Chunk, GameMap, GameObject}

import scala.math._
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.mein.kraft.core.Command.CommandInvoker
import com.mein.kraft.core.objectGenerator.Fonts
import com.mein.kraft.core.plugintools._

class CoreListener extends Screen {

  //<editor-fold="params">

  //val plugin_List: File = new File("pluginsconfig.cfg")
  //PluginLoader.parseConfig(plugin_List, "")

  //val pluginDatas: util.HashMap[String, PluginData] = PluginLoader.getPlugins()

  //pluginDatas.forEach((pluginName, plugin) => {
    //println("Plugin: " + pluginName)
    //PluginLoader.newInstance(plugin).start()
  //})


  var newGame = true
  val config = new Configurator("main.properties")
  val chunkTemoin: Chunk = new Chunk
  var instances: Array[GameObject] = new Array[GameObject]()
  var instancesToPhysics: Array[GameObject] = new Array[GameObject]()

  /**
   * * Sounds & music attributes
   **/
  //val music_prop = config.getConfig("soundBackground")
  //val music: Music = Gdx.audio.newMusic(Gdx.files.internal(music_prop))
  //music.play()

  /**
   * * All Controllers of our game here
   **/
  val gameMode = new GameMode()
  val mapController = new MapController
  val lightController = new LightControler
  val timeCycleController = new TimeCycleController(lightController)
  var physicsController = new PhysicsController(gameMode)
  val playerController = new PlayerControler(gameMode)
  val fonts = new Fonts

  gameMode.subscribe(playerController.gamePlayer)
  gameMode.subscribe(physicsController)

  /**
   * * Arrays and Batches
   * * graphics
   **/
  val renderDistance = config.getConfig("render_distance").toInt
  var modelBatch: ModelBatch = new ModelBatch()
  var stage = new Stage
  var label = new Label(" ", new Label.LabelStyle(fonts.getKraftFont(0.5f), Color.WHITE))
  var label2 = new Label(" ", new Label.LabelStyle(fonts.getDotedFont(0.8f), Color.GOLD))
  label.setPosition(10, Gdx.graphics.getHeight - 30)
  label2.setPosition(10, Gdx.graphics.getHeight - 55)
  stage.addActor(label)
  stage.addActor(label2)

  /**
   * * Initialisation
   **/
  loadNewMap()
  setPlayerSpawn()

  instances.add(playerController.gamePlayer.characterObject)
  physicsController.addObject(playerController.gamePlayer.characterObject)
  playerController.gamePlayer.initActionListener(playerController.gamePlayer, instances, playerController.gamePlayerHUD.aimingCross: Texture, physicsController, gameMode)

  //</editor-fold>

  //<editor-fold="load methods">

  def setPlayerSpawn(): Unit = {
    playerController.gamePlayer.setSpawn(10*2, mapController.map.chunkSet(0,0).getGroundLevel(10,10)*2-2, 10*2)
  }

  /**
   * * When loading is done we render the blocks using the MapGenerator
   **/
  private def loadNewMap(): Unit = {
    if(newGame) {
      mapController.map = new GameMap
      mapController.map.addToChunkSet(mapController.generateChunk((0, 0)), (0, 0))
      loadChunk((0, 0))
      newGame = false
    }
  }

  private def loadNewChunk(coord: (Int, Int)): Unit = {
    mapController.map.addToChunkSet(mapController.generateChunk(coord), coord)
    loadChunk(coord)
  }

  private def loadChunk(coord: (Int, Int)): Unit = {
    val bounds: BoundingBox = new BoundingBox
    for (x <- 0 until mapController.map.chunkSet(coord).length) {
      for (y <- 0 until mapController.map.chunkSet(coord).width) {
        for (z <- 0 until mapController.map.chunkSet(coord).height) {
          val block = mapController.map.chunkSet(coord).blockSet(x)(y)(z)
          if (block != null) {
            block.calculateBoundingBox(bounds)
            val blockShape = new Box(bounds)
            block.shape = blockShape
            instances.add(block)
          }
        }
      }
    }
  }

  def generateNeighborChunks: Unit = {
    val currentChunkCoords: (Int, Int) = (floor((playerController.gamePlayer.camera.position.x / 2) / chunkTemoin.length).toInt,
      floor((playerController.gamePlayer.camera.position.z / 2) / chunkTemoin.width).toInt)
    val neighborsChunkCoords: Array[(Int, Int)] = new Array[(Int, Int)]()
    for (i <- 1 - renderDistance until renderDistance) {
      for (j <- 1 - renderDistance until renderDistance) {
        neighborsChunkCoords.add((currentChunkCoords._1 + i, currentChunkCoords._2 + j))
      }
    }
    for (i <- 0 until neighborsChunkCoords.size) {
      if (!mapController.map.checkMapContains(neighborsChunkCoords.get(i))) {
        loadNewChunk(neighborsChunkCoords.get(i))
      }
    }
  }

  def generateNeighboorBlocksPhysics: Unit = {
    val neighboorBlocks: Array[GameObject] = findNeighboorBlock(
      (playerController.gamePlayer.camera.position.x / 2).toInt,
      (playerController.gamePlayer.camera.position.y / 2).toInt,
      (playerController.gamePlayer.camera.position.z / 2).toInt)

    for (i <- 0 until neighboorBlocks.size) {
      if (!instancesToPhysics.contains(neighboorBlocks.get(i), true)) {
        instancesToPhysics.add(neighboorBlocks.get(i))
        physicsController.addObject(neighboorBlocks.get(i))
      }
    }
  }

  //</editor-fold>

  def applyEnvironnementForces(): Unit = {

  }

  //<editor-fold="visual render">

  override def render(delta: Float) = {
    //if (newGame) loadNewMap()
    //if(loadGame) getSave()

    playerController.handlePlayerMovements()
    applyEnvironnementForces()

    //var force: Float = 30.0f;   //but any value that works for you
    //var dir:Vector3 = new Vector3(force,force,force); //suppose your camera moves around
    //playerController.gamePlayer.characterObject.body.applyCentralImpulse(dir)

    generateNeighborChunks
    generateNeighboorBlocksPhysics

    val worldDelta = Math.min(1f / 30f, delta)
    physicsController.getConcreteWorld().stepSimulation(worldDelta, 5, 1f / 60)

    gl.glViewport(0, 0, graphics.getWidth, graphics.getHeight)
    gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT)

    modelBatch.begin(playerController.gamePlayer.camera)
    for (x <- 0 until instances.size) {
      if(playerController.gamePlayer.characterObject == instances.get(x)){
        modelBatch.render(instances.get(x), lightController.lights)
      }
      if (isInFeildOfVew(playerController.gamePlayer.camera, instances.get(x)) && !instances.get(x).isolated) {
        modelBatch.render(instances.get(x), lightController.lights)
      }
    }
    if (timeCycleController.skybox != null) {
      timeCycleController.skybox.transform.setToTranslation(playerController.gamePlayer.camera.position.x, playerController.gamePlayer.camera.position.y, playerController.gamePlayer.camera.position.z)
      modelBatch.render(timeCycleController.skybox, lightController.sky)
    }
    modelBatch.end()

    playerController.gamePlayerHUD.updateCrossHair()

    label.setText("Time elapsed: " + timeCycleController.hours + " : " + timeCycleController.minutes + " : " + timeCycleController.seconds)
    label2.setText(playerController.gamePlayer.actionListener.blocSelectInputProc.selectedBlock)
    stage.draw()

    CommandInvoker.unstack()
  }

  //</editor-fold>

  //<editor-fold="optimisation">

  def isInFeildOfVew(camera: PerspectiveCamera, blockInstance: GameObject): Boolean = {
    blockInstance.transform.getTranslation(blockInstance.positionVector)
    camera.frustum.boundsInFrustum(blockInstance.positionVector, blockInstance.renderDimensions)
  }

  def findNeighboorBlock(x: Int, y: Int, z: Int): Array[GameObject] = {
    val neighboorBlocks: Array[GameObject] = new Array[GameObject]()
    for (i <- 0 until instances.size) {
      val block: GameObject = instances.get(i)
      if (block.xCoord >= x - 2 && block.xCoord <= x + 2) {
        if (block.yCoord >= z - 2 && block.yCoord <= z + 2) {
          if (block.zCoord >= y - 2 && block.zCoord <= y + 2) {
            neighboorBlocks.add(block)
          }
        }
      }
    }
    neighboorBlocks
  }

  //</editor-fold>
  override def dispose(): Unit = {
    modelBatch.dispose()
    for (go <- instances.items) {
      go.dispose()
    }
    physicsController.dispose()
    //music.dispose()
  }

  override def resume(): Unit = {}

  override def resize(width: Int, height: Int): Unit = {}

  override def pause(): Unit = {}

  override def show(): Unit = {}

  override def hide(): Unit = {}
}
