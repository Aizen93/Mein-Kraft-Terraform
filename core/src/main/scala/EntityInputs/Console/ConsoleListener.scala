package com.mein.kraft.core.Console

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.utils.Array
import com.mein.kraft.core.Command.CommandInvoker
import com.mein.kraft.core.Command.Interactions.{ExitGameCommand, SwitchGameModeCommand, TeleportCommand}
import com.mein.kraft.core.GameMode.GameMode
import com.mein.kraft.core.GamePlayer
import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.mapGenerator.GameObject

class ConsoleListener(gameMode: GameMode, player : GamePlayer, instances : Array[GameObject]) extends InputAdapter {
  val config = new Configurator("main.properties")
  private var _command: String = ""

  def command: String = _command

  def treatCommand: Unit = {
    _command match {
      case "gameMode0" => CommandInvoker.addCommand(new SwitchGameModeCommand(gameMode, 0))
      case "gameMode1" => CommandInvoker.addCommand(new SwitchGameModeCommand(gameMode, 1))
      case "exitGame"  => CommandInvoker.addCommand(new ExitGameCommand())
      case "teleport"  => if(player.findNeighboorVoidBlock(instances)) CommandInvoker.addCommand(new TeleportCommand(0, 40, 0, player))
      case default => print("No command !\n")
    }
  }

  override def keyDown(keyCode: Int): Boolean = {
    if (keyCode == config.getConfig("game_mode0").toInt) { //0 on keyboard
      _command = "gameMode0"
      treatCommand
    }
    if (keyCode == config.getConfig("game_mode1").toInt) { //1 on keyboard
      _command = "gameMode1"
      treatCommand
    }
    if(keyCode == config.getConfig("exit").toInt){
      _command = "exitGame"
      treatCommand
    }
    if(keyCode == 48){
      _command = "teleport"
      treatCommand
    }
    true
  }
}
