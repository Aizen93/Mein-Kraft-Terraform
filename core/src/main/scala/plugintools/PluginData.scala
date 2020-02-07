package com.mein.kraft.core.plugintools

import java.net.{URL, URLClassLoader}

@throws(classOf[ClassNotFoundException])
class PluginData(val name: String, val pack: String, val file: URL) {
  val cache = Class.forName(pack, true, new URLClassLoader(Array(file)))
}
