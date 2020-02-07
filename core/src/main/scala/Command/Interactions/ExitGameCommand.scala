package com.mein.kraft.core.Command.Interactions

import com.badlogic.gdx.Gdx
import com.mein.kraft.core.Command.ICommand

class ExitGameCommand extends ICommand{
  override def execute(): Unit = {
    Gdx.app.exit()
  }
}
