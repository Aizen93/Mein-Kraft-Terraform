package com.mein.kraft.core.Inputs

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Vector3
import com.mein.kraft.core.mapGenerator.GameObject

class CharacterMotorSurvival(playerInput: ICharacterMovementInput, playerSettings: CharacterSettings, playerCamera: Camera, characterObject: GameObject)
  extends AnyRef with ICharacterMotor {

  val moveSpeed: Float = 4
  val CHARACTER_JUMP_SPEED = 5

  def move: Unit = {

    val frontMove: Vector3 = new Vector3(playerSettings.moveSpeed * playerCamera.direction.x * (playerInput._frontSpeed + playerInput._boost),
      0, playerSettings.moveSpeed * playerCamera.direction.z * (playerInput._frontSpeed + playerInput._boost))

    val backMove: Vector3 = new Vector3(playerSettings.moveSpeed  * -playerCamera.direction.x * (playerInput._backSpeed),
      0, playerSettings.moveSpeed * -playerCamera.direction.z * playerInput._backSpeed)

    val leftMove: Vector3 = new Vector3(playerInput._leftSpeed * playerSettings.moveSpeed * playerCamera.direction.z,
      0, playerInput._leftSpeed * playerSettings.moveSpeed * -playerCamera.direction.x)

    val rightMove: Vector3 = new Vector3(playerInput._rightSpeed * playerSettings.moveSpeed * -playerCamera.direction.z,
      0, playerInput._rightSpeed * playerSettings.moveSpeed * playerCamera.direction.x)

    var jump: Vector3 = new Vector3
    if(characterObject.body.getLinearVelocity.y >= 1.0E-8 && characterObject.body.getLinearVelocity.y <= CHARACTER_JUMP_SPEED) {
      jump = new Vector3(0, CHARACTER_JUMP_SPEED * playerInput._jump, 0)
    }

    val setHeight = new Vector3(0, characterObject.body.getLinearVelocity.y, 0)

    val globalMove = new Vector3(frontMove.add(backMove).add(leftMove).add(rightMove).add(jump).scl(2))

    makeMove(globalMove.add(setHeight))
  }

  def makeMove(globalMove: Vector3): Unit = {
    characterObject.body.setLinearVelocity(globalMove)
  }
}
