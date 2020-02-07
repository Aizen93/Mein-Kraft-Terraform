package com.mein.kraft.core.Inputs

class CharacterSettings {

  var moveSpeed: Float = 4f
  var useAI: Boolean = false

  def setCreative: Unit = {
    moveSpeed = 6f
  }

  def setSurvival: Unit = {
    moveSpeed = 4f
  }

}
