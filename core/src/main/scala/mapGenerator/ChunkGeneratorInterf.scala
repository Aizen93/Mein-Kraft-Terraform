package com.mein.kraft.core.mapGenerator

trait ChunkGeneratorInterf {

  def generateNoiseChunk(frequency: Int, amplitude: Int): Array[Array[Array[GameObject]]]

  def noise(x: Double, y: Double, amplitude: Int): Int

  def fillColumnFromHighestBlock(chunk: Array[Array[Array[GameObject]]], x: Int, y: Int, z: Int): Array[Array[Array[GameObject]]]

  def setFloorToZero(z: Int): Int

  def grad(hash: Int, x: Double, y: Double): Double

  def fade(t: Double): Double

  def lerp(amount: Double, left: Double, right: Double): Double



}
