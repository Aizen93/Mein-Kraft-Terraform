package com.mein.kraft.core.mapGenerator

trait IBiome {

  def biomeType: String
  def surfaceBlocType: String
  def underBlocType: String
  def amplitude: Int
  def frequence: Int

  def seed: Boolean
  def hasTrees: Boolean
  def hasCaves: Boolean

  def loadBiomeInfo(): Unit
}
