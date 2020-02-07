package com.mein.kraft.core.Command.Interactions

import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld
import com.mein.kraft.core.Command.ICommand

class SetGravityCommand(dynamicsWorld: btDynamicsWorld, gravity: Vector3) extends ICommand{
  override def execute(): Unit = {
    dynamicsWorld setGravity gravity
  }
}
