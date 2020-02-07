package com.mein.kraft.core.mapGenerator

import scala.math._
import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.mapGenerator.Block.{BlockBuilder, TextureBuilder}

/** Generates chunks
 */
class ChunkGenerator {

  private val config = new Configurator("main.properties")
  private var chunk: Chunk = _

  private val blockBuilder = new BlockBuilder
  private val textureBuilder : TextureBuilder = new TextureBuilder

  //<editor-fold="Params">

  /** Permutation table, set to choose random grid vectors
   */
  private val perm1: Array[Int] = new Array[Int](256)
  for (i <- 1 until 256){
    perm1(i)=i
  }
  /** Fills the complete permutation table
   */
  private val perm = perm1 ++ perm1

  //</editor-fold>

  //<editor-fold="Constructor">

  /** random arrangement of the permutation table following the Fisher Yates algorithm
   */
  fisherYatesPermutation()

  //</editor-fold>

  //<editor-fold="PerlinNoise3DL">

  def generateNoiseChunk(coords: (Int, Int), biomeInfo: Array[Array[IBiome]]): Chunk = {
    chunk = new Chunk
    //fisherYatesPermutation()
    for (y <- 0 until chunk.length) {
      for (x <- 0 until chunk.width) {

        val dx = (x.toDouble / chunk.length) + coords._1*biomeInfo(x)(y).frequence
        val dy = (y.toDouble / chunk.width) + coords._2
        val z = setFloorToZero(noise(dx * biomeInfo(x)(y).frequence, dy * biomeInfo(x)(y).frequence, biomeInfo(x)(y).amplitude))

        textureBuilder.selectMaterial(biomeInfo(x)(y).surfaceBlocType)
        textureBuilder.applyMaterial()
        val newSurfaceBlock = new GameObject(textureBuilder.getblockModel(), x + coords._1 * chunk.length, y + coords._2 * chunk.width, z)
        chunk.storeInBlockSet(newSurfaceBlock, x, y, z)
        blockBuilder.placeBlock(newSurfaceBlock)

        for (zi <- 0 until z) {
          textureBuilder.selectMaterial(biomeInfo(x)(y).underBlocType)
          textureBuilder.applyMaterial()
          val newUnderBlock = new GameObject(textureBuilder.getblockModel(), x + coords._1 * chunk.length, y + coords._2 * chunk.width, zi)
          chunk.storeInBlockSet(newUnderBlock, x, y, zi)
          blockBuilder.placeBlock(newUnderBlock)
        }
      }
    }
    renderOptimisation()
    chunk
  }

  /** Noise function that gives the shape of the generated land
   */
  private def noise(x: Double, y: Double, amplitude: Int): Int = {

    val xi: Double = abs(math.floor(x) % 256)
    val yi: Double = abs(math.floor(y) % 256)

    val g1: Int = perm(perm(xi.toInt) + yi.toInt)
    val g2: Int = perm(perm(xi.toInt + 1) + yi.toInt)
    val g3: Int = perm(perm(xi.toInt) + yi.toInt + 1)
    val g4: Int = perm(perm(xi.toInt + 1) + yi.toInt + 1)

    val xf: Double = x - math.floor(x)
    val yf: Double = y - math.floor(y)

    val d1: Double = grad(g1, xf, yf)
    val d2: Double = grad(g2, xf - 1, yf)
    val d3: Double = grad(g3, xf, yf - 1)
    val d4: Double = grad(g4, xf - 1, yf - 1)

    val u: Double = fade(xf)
    val v: Double = fade(yf)

    val x1Inter: Double = lerp(u, d1, d2)
    val x2Inter: Double = lerp(u, d3, d4)

    val yInter: Double = lerp(v, x1Inter, x2Inter)

    (yInter * amplitude).toInt
  }

  //</editor-fold>

  //<editor-fold="CalculationMethods">

  /** Fills the complete permutation table
   */
  def setFloorToZero(z: Int): Int = {
    var setZ: Int = 0
    setZ = z + config.getConfig("max_amplitude").toInt
    setZ
  }

  /** Makes scalar product
   */
  private def grad(hash: Int, x: Double, y: Double): Double = {
    hash % 4 match {
      case 0 => x + y
      case 1 => -x + y
      case 2 => x + -y
      case 3 => -x + -y
    }
  }

  /** fade function used for linear interpolation
   */
  private def fade(t: Double): Double = {
    t * t * t * t * (t * (t * 6 - 15) + 10)
  }

  /** polynomial interpolation
   */
  private def lerp(amount: Double, left: Double, right: Double): Double = {
    (1 - amount) * left + amount * right
  }

  def fisherYatesPermutation(): Unit = {
    for (x <- 0 to perm.length - 1) {
      val r: Double = (System.currentTimeMillis()*math.random * 256) % 256
      perm(x) = perm(r.toInt)
    }
  }

  //</editor-fold>

  //<editor-fold="OptimisationMethods">

  def renderOptimisation(): Unit = {
    addInfoIsIsolated()
    addInfoIsOnEdge()
  }

  def addInfoIsIsolated(): Unit = {
    for (x <- 1 until chunk.length-2) {
      for (y <- 1 until chunk.width-2) {
        for (z <- 1 until chunk.height-2) {

          if(chunk.blockSet(x)(y)(z + 1)!=null  && chunk.blockSet(x)(y)(z - 1)!=null
            && chunk.blockSet(x)(y + 1)(z)!=null && chunk.blockSet(x)(y - 1)(z)!=null
            && chunk.blockSet(x + 1)(y)(z)!=null && chunk.blockSet(x - 1)(y)(z)!=null)
          {
            chunk.blockSet(x)(y)(z).isolated = true
          }
        }
      }
    }
  }

  def addInfoIsOnEdge(): Unit = {
    for (x <- 0 until chunk.length) {
      for (y <- 0 until chunk.width) {
        for (z <- 0 until chunk.height) {

          if(z==0 && chunk.blockSet(x)(y)(z) != null){
            chunk.blockSet(x)(y)(z).isolated = true
          }
        }
      }
    }
  }
  //</editor-fold>
}

