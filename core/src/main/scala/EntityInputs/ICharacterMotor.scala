package com.mein.kraft.core.Inputs

import com.badlogic.gdx.math.Vector3

trait ICharacterMotor {

  def move: Unit

  def makeMove(globalMove: Vector3): Unit
}
