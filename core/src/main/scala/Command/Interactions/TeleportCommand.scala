package com.mein.kraft.core.Command.Interactions

import com.badlogic.gdx.math.Vector3
import com.mein.kraft.core.Command.ICommand
import com.mein.kraft.core.GamePlayer

class TeleportCommand(x: Int, y: Int, z: Int, gamePlayer: GamePlayer) extends ICommand {

  override def execute(): Unit = {

      gamePlayer.characterObject.body.setWorldTransform(gamePlayer.characterObject.body.getWorldTransform.setToTranslation(new Vector3(x,y,z)))

  }

}
