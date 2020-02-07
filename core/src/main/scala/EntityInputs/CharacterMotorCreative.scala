package com.mein.kraft.core.Inputs

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Vector3
import com.mein.kraft.core.mapGenerator.GameObject

class CharacterMotorCreative(playerInput: ICharacterMovementInput, playerSettings: CharacterSettings, playerCamera: Camera, characterObject: GameObject)
  extends AnyRef with ICharacterMotor {

  val _goUpSpeed: Float = 15
  val _goDownSpeed: Float = 15

  def move: Unit = {

    val frontMove: Vector3 = new Vector3(playerInput._frontSpeed * playerSettings.moveSpeed * playerCamera.direction.x,
      0, playerInput._frontSpeed * playerSettings.moveSpeed * playerCamera.direction.z)

    val backMove: Vector3 = new Vector3(playerInput._backSpeed * playerSettings.moveSpeed * -playerCamera.direction.x,
      0, playerInput._backSpeed * playerSettings.moveSpeed * -playerCamera.direction.z)

    val leftMove: Vector3 = new Vector3(playerInput._leftSpeed * playerSettings.moveSpeed * playerCamera.direction.z,
      0, playerInput._leftSpeed * playerSettings.moveSpeed * -playerCamera.direction.x)

    val rightMove: Vector3 = new Vector3(playerInput._rightSpeed * playerSettings.moveSpeed * -playerCamera.direction.z,
      0, playerInput._rightSpeed * playerSettings.moveSpeed * playerCamera.direction.x)

    val upMove: Vector3 = new Vector3(0, _goUpSpeed * playerInput._jump, 0)

    val downMove: Vector3 = new Vector3(0, _goDownSpeed * -playerInput._crouch, 0)

    val setHeight = new Vector3(0, characterObject.body.getLinearVelocity.y, 0)

    val globalMove = new Vector3(frontMove.add(backMove).add(leftMove).add(rightMove).add(upMove).add(downMove).scl(2))

    makeMove(globalMove)
  }

  def makeMove(globalMove: Vector3): Unit = {
    characterObject.body.setLinearVelocity(globalMove)
  }
}
