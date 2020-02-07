package com.mein.kraft.core.mapGenerator

trait ChunkInterf {
  // def Chunk_=( length: Int , width: Int , height: Int):Unit
  var length: Int = 32
  var width: Int = 32
  var height: Int = 64

  var blockSet = Array.ofDim[GameObject](length, width, height)

  def blockSetSet(value :GameObject  ):Unit

  def generateBlock(value :GameObject ):Unit

  def generateGlobal(value :GameObject ):Unit

}
