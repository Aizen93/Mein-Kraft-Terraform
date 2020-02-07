package com.mein.kraft.core.Controler

import com.badlogic.gdx.utils.Timer

trait ITimeCycle[A] {

  val skybox : A
  val timer : Timer.Task

}
