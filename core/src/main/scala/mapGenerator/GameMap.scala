package com.mein.kraft.core.mapGenerator


class GameMap {

  var chunkSet: Map[(Int,Int),Chunk] = Map()

  def addToChunkSet(chunk: Chunk, coord: (Int,Int)): Unit = {
    chunkSet += (coord -> chunk)
  }

  def deleteFromChunkSet(coord: (Int,Int)): Unit = {
    chunkSet = chunkSet.-(coord)
  }

  def checkMapContains(coord: (Int,Int)): Boolean = {
    chunkSet.contains(coord)
  }
}
