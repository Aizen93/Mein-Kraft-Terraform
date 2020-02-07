package com.mein.kraft.core.objectGenerator

import com.mein.kraft.core.mapGenerator.Chunk


trait TreeInterf {

  //var size: Int=0
  var chunk: Chunk =  _//new Chunk
  //var position: Block= _//new Block
  def creation():Unit

  def squarreRandomLfoliage(lFoliage: Int, trunkLimit: Int, size: Int): Unit

  def triangleLfoliage(lFoliage: Int, trunkLimit: Int, size: Int): Unit

  def binaryGeneration(u: Int, v: Int): Unit

  def unaryGeneration(e: Int): Unit



}
