package com.mein.kraft.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mein.kraft.core.menu.{MainScreen}
import com.mein.kraft.core.configurator.Configurator

object Main extends App {
  val config = new Configurator("main.properties")

  val configGdx = new LwjglApplicationConfiguration
  configGdx.title = config.getConfig("title")
  configGdx.width = config.getConfig("screen_width").toInt
  configGdx.height = config.getConfig("screen_height").toInt

  val app = new LwjglApplication(new MainScreen , configGdx)
}
