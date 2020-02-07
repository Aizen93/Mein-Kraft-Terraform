package com.mein.kraft.core.Controler

import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.mapGenerator.{BiomeGenerator, Chunk, ChunkGenerator, GameMap}

class MapController {

  //<editor-fold="Params">

  private val config = new Configurator("main.properties")
  var map: GameMap = _
  val chunkGenerator = new ChunkGenerator
  val biomeGenerator = new BiomeGenerator

  //</editor-fold>

  //<editor-fold="Generator">

  //Amplitude max = 15
  def generateChunk(coords: (Int,Int)): Chunk = {
    var chunkbiomeInfo = biomeGenerator.generateNoiseBiomeInfo(coords) //, map.getBordersBiomeInfo(coords))
    chunkGenerator.generateNoiseChunk(coords, chunkbiomeInfo) //, map.getBordersChunkInfo(coords))

  }

  //</editor-fold>

}
