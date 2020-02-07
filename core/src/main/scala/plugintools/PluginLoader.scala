package com.mein.kraft.core.plugintools

import java.io.{BufferedReader, File, FileInputStream, IOException, InputStreamReader}
import java.util.HashMap

import scala.io.Source

object PluginLoader {
  private val data = new HashMap[String, PluginData]

  @throws(classOf[IOException])
  @throws(classOf[ClassNotFoundException])
  def parseConfig(file: File, pluginRoot: String): Unit = {
    val source = Source.fromFile(file)
    var lineNb: Int = 0

    for(line <- source.getLines()) {
      lineNb += 1
      val split = line.split(" ")
      val fileplugin = new File(pluginRoot, split(0) + ".jar").toURI.toURL
      data.put(split(0), new PluginData(split(0), split(1), fileplugin))
    }
    source.close()
  }

  @throws(classOf[InstantiationError])
  @throws(classOf[IllegalAccessException])
  def newInstance(data: PluginData): PluginAbstract = {
    data.cache.newInstance().asInstanceOf[PluginAbstract]
  }

  def getPlugins(): HashMap[String, PluginData] = {
    PluginLoader.data
  }
}
