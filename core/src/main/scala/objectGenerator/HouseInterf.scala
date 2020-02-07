package com.mein.kraft.core.objectGenerator

import com.mein.kraft.core.mapGenerator.{Chunk, GameObject}

abstract class HouseInterf {
  var chunk: Chunk =  _
  var height : Int = 0
  var width : Int = 0
  var length : Int = 0
  var position : GameObject = _
}
