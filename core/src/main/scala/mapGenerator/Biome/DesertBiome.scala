package com.mein.kraft.core.mapGenerator

class DesertBiome extends IBiome {

  val seed: Boolean = false
  val biomeType = "desert"
  val surfaceBlocType = "grass"
  val underBlocType = "grass"
  val amplitude = 8
  val frequence = 2

  val hasTrees = true
  val hasCaves = true

  override def loadBiomeInfo(): Unit = {

  }
}
