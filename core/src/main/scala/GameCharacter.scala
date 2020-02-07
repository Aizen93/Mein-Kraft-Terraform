package com.mein.kraft.core

import com.badlogic.gdx.graphics.g3d.Model
import com.mein.kraft.core.Inputs.{CharacterSettings, ICharacterMotor, ICharacterMovementInput}
import com.mein.kraft.core.mapGenerator.GameObject

trait GameCharacter {

  var characterObject: GameObject
  var model: Model

  var characterMovementInput: ICharacterMovementInput
  var characterMotor: ICharacterMotor
  var characterSettings: CharacterSettings

  def updateMovements: Unit

}
