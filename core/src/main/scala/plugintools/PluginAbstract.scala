package com.mein.kraft.core.plugintools

abstract class PluginAbstract extends Thread {
  def run() : Unit
  def close() : Unit
}
