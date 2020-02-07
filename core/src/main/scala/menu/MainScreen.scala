package com.mein.kraft.core.menu

import com.badlogic.gdx.{Game}

class MainScreen extends Game {
  override def create() : Unit = {
    this.setScreen(new Menu(this))
  }

  override def render(): Unit = {
    super.render()
  }

  override def dispose() : Unit = {}
}
