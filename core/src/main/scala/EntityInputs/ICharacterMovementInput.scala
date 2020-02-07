package com.mein.kraft.core.Inputs

trait ICharacterMovementInput {

  var _boost: Float = 0
  var _frontSpeed: Float = 0
  var _backSpeed: Float = 0
  var _leftSpeed: Float = 0
  var _rightSpeed: Float = 0
  var _jump: Float = 0
  var _crouch: Float = 0

  def readMovementInput: Unit
}
