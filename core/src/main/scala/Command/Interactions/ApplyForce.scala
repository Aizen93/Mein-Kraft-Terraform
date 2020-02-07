package com.mein.kraft.core.Command.Interactions

import com.badlogic.gdx.math.Vector3
import com.mein.kraft.core.Command.ICommand
import com.mein.kraft.core.mapGenerator.GameObject

class ApplyForce(characterObject: GameObject, force: Vector3) extends ICommand{
  override def execute(): Unit = {
    characterObject.body.applyCentralImpulse(force)
  }
}
