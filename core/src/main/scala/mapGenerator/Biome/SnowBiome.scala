package com.mein.kraft.core.mapGenerator

import com.mein.kraft.core.configurator.Configurator

class SnowBiome extends IBiome {

  val seed = false
  val biomeType = "snow"
  val surfaceBlocType = "grass"
  val underBlocType = "grass"
  val amplitude = 10
  val frequence = 1

  val hasTrees = true
  val hasCaves = true

  override def loadBiomeInfo(): Unit = {

  }
}
