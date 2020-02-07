package com.mein.kraft.core.configurator

trait ConfiguratorTrait {
  def getConfig(key: String): String
  def setConfig(key : String, value: String): Unit
}
