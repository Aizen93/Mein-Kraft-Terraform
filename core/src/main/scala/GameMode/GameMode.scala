package com.mein.kraft.core.GameMode

import com.mein.kraft.core.ObserverAbstract.{Observer, Subject}

class GameMode() extends AnyRef with Subject {

  def subscribe(observer: Observer): Unit ={
    attach(observer)
  }
}
