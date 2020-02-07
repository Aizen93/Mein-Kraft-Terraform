package com.mein.kraft.core
import com.badlogic.gdx.graphics.g3d.Model
import com.mein.kraft.core.Inputs.{CharacterSettings, ICharacterMotor, ICharacterMovementInput}
import com.mein.kraft.core.mapGenerator.GameObject

class GameMob extends GameCharacter {

  var characterObject: GameObject = _
  var model: Model = _

  var characterMovementInput: ICharacterMovementInput = _
  var characterMotor: ICharacterMotor = _
  var characterSettings: CharacterSettings = _

  override def updateMovements: Unit = {

  }
}
