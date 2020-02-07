package com.mein.kraft.core.configurator

import java.io.FileReader
import java.io.IOException
import java.util.Properties

class Configurator(path: String) extends ConfiguratorTrait {
  val properties = new Properties
  load(path)

  def getConfig(key: String) : String = {
    properties.getProperty(key)
  }

  def setConfig(key : String, value: String) : Unit = {
    properties.setProperty(key, value)
    ()
  }

  @throws(classOf[IOException])
  def load(path : String) : Unit = {
    val reader = new FileReader(path)
    properties.load(reader)
    reader.close()
  }

  def getProperties() : Properties = {
    properties
  }
}
