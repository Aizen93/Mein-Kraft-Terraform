package com.mein.kraft.core.Controler

import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.GameMode.GameMode
import com.mein.kraft.core.{GamePlayer, GamePlayerHUD}
import com.mein.kraft.core.mapGenerator.{GameObject}

class PlayerControler(gameMode: GameMode) {

  val config = new Configurator("main.properties")

  var gamePlayer: GamePlayer = new GamePlayer(gameMode)
  var gamePlayerHUD: GamePlayerHUD = new GamePlayerHUD

  def distance(obj: GameObject): Double = {
    math.sqrt(math.pow(math.abs(gamePlayer.camera.position.x / 2 - obj.xCoord), 2) + math.pow(math.abs(gamePlayer.camera.position.y / 2 - obj.yCoord), 2)
      + math.pow(math.abs(gamePlayer.camera.position.z / 2 - obj.zCoord), 2))
  }

  def handlePlayerMovements(): Unit = {
    gamePlayer.updateMovements()
    gamePlayer.camera.position.set(gamePlayer.characterObject.body.getCenterOfMassPosition)
    gamePlayer.camera.update()
  }
}
