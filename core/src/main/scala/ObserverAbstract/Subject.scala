package com.mein.kraft.core.ObserverAbstract

import com.badlogic.gdx.utils.Array

trait Subject {

  private val observers = new Array[Observer]
  private var state = 0

  def getState(): Int = {
    state
  }

  def setState(state: Int): Unit = {
    this.state = state
    notifyAllObservers()
  }

  def attach(observer: Observer): Unit = {
    observers.add(observer)
  }

  def notifyAllObservers(): Unit = {
    observers.forEach(_.update())
  }
}
