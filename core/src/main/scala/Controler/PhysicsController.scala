package com.mein.kraft.core.Controler

import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.bullet.Bullet
import com.badlogic.gdx.physics.bullet.collision._
import com.badlogic.gdx.physics.bullet.dynamics.{btDiscreteDynamicsWorld, btDynamicsWorld, btSequentialImpulseConstraintSolver}
import com.mein.kraft.core.Command.CommandInvoker
import com.mein.kraft.core.Command.Interactions.SetGravityCommand
import com.mein.kraft.core.GameMode.GameMode
import com.mein.kraft.core.ObserverAbstract.Observer
import com.mein.kraft.core.mapGenerator.{GameObject}

class PhysicsController(gameMode: GameMode) extends AnyRef with Observer {

  Bullet.init
  var collisionCenterVector = new Vector3(-1,0,0)
  var collisionConfig: btCollisionConfiguration = new btDefaultCollisionConfiguration
  var dispatcher: btCollisionDispatcher = new btCollisionDispatcher(collisionConfig)
  var sweep: btAxisSweep3 = new btAxisSweep3(new Vector3(-1000, -1000, -1000), new Vector3(1000, 1000, 1000))
  var constraintSolver: btSequentialImpulseConstraintSolver = new btSequentialImpulseConstraintSolver
  var dynamicsWorld: btDynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, sweep, constraintSolver, collisionConfig)
  dynamicsWorld setGravity new Vector3(0, -20f, 0)

  subject = this.gameMode
  subject.attach(this)

  override def update(): Unit = {
    if(subject.getState() == 0){
      CommandInvoker.addCommand(new SetGravityCommand(dynamicsWorld, new Vector3(0, -20f, 0)))
    }
    if(subject.getState() == 1){
      CommandInvoker.addCommand(new SetGravityCommand(dynamicsWorld, new Vector3(0, 0f, 0)))
    }
  }

  def getConcreteWorld() : btDynamicsWorld = {
      dynamicsWorld
  }

  def addObject(obj: GameObject):Unit ={
    obj.body.proceedToTransform(obj.transform)
    obj.body.translate(collisionCenterVector)

    obj.body.setContactCallbackFlag(btCollisionObject.CollisionFlags.CF_NO_CONTACT_RESPONSE)
    dynamicsWorld.addRigidBody(obj.body)
  }

  def removeObject(obj: GameObject):Unit ={
    dynamicsWorld.removeRigidBody(obj.body)
  }

  def dispose() : Unit = {
    collisionConfig.dispose()
    dispatcher.dispose()
    sweep.dispose()
    constraintSolver.dispose()
    dynamicsWorld.dispose()
  }
}
