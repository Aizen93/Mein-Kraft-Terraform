package com.mein.kraft.core.mapGenerator

class BiomeGenerator {

  var chunkTemoin: Chunk = new Chunk()

  def generateNoiseBiomeInfo(coords: (Int, Int)): Array[Array[IBiome]] = {
    var biomeInfoArray = Array.ofDim[IBiome](chunkTemoin.length, chunkTemoin.width)

    setBiomeSeedPositions(coords, biomeInfoArray)
    for (x <- 0 until chunkTemoin.length) {
      for (y <- 0 until chunkTemoin.width) {
        // calculateBiomeInfo(coords, x, y)
        biomeInfoArray(x)(y) = new DesertBiome()
      }
    }

    //Generation logic

    biomeInfoArray
  }

  def setBiomeSeedPositions(coords: (Int, Int), BiomeArray: Array[Array[IBiome]]): Array[Array[IBiome]] = {
    for (x <- 0 until chunkTemoin.length) {
      for (y <- 0 until chunkTemoin.width) {
        //var closestSeed = getClosestSeed(seedsArray, x, y)
        //if ((generateSeed(closestSeed.x + closestSeed.y))) {
        //BiomeArray(x)(y) = new randomBiome
      }
    }
    BiomeArray
  }
}
