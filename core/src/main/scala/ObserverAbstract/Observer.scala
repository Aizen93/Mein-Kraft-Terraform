package com.mein.kraft.core.ObserverAbstract

trait Observer {

  protected var subject: Subject = _

  def update(): Unit
}