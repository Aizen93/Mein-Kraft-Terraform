package com.mein.kraft.core.Command.Interactions

import com.mein.kraft.core.Command.ICommand
import com.mein.kraft.core.GameMode.GameMode

class SwitchGameModeCommand(gameMode: GameMode, newMode: Int) extends ICommand {

  override def execute(): Unit = {
    gameMode.setState(newMode)
  }
}
