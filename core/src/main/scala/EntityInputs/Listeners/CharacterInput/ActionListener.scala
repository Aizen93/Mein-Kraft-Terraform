package com.mein.kraft.core.Inputs.Listeners.PlayerListeners

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.{Gdx, InputMultiplexer}
import com.mein.kraft.core.Console.ConsoleListener
import com.mein.kraft.core.Controler.PhysicsController
import com.mein.kraft.core.GameMode.GameMode
import com.mein.kraft.core.GamePlayer
import com.mein.kraft.core.mapGenerator.Block.TextureBuilder
import com.mein.kraft.core.mapGenerator.GameObject

class ActionListener(gamePlayer: GamePlayer,
                     instancesObjects: Array[GameObject],
                     aimingCross: Texture, world: PhysicsController,
                     texture: TextureBuilder,
                     gameMode: GameMode) {

  val multiplexer = new InputMultiplexer
  val cameraInputProc = new CameraInput(gamePlayer, instancesObjects, aimingCross, world, texture)
  val blocSelectInputProc = new BlockSelectInput(texture)
  val consoleListener = new ConsoleListener(gameMode, gamePlayer, instancesObjects)
  multiplexer.addProcessor(cameraInputProc)
  multiplexer.addProcessor(blocSelectInputProc)
  multiplexer.addProcessor(consoleListener)
  Gdx.input.setInputProcessor(multiplexer)

}
