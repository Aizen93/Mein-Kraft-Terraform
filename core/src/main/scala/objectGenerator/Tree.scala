package com.mein.kraft.core.objectGenerator

import com.badlogic.gdx.graphics.g3d.Model
import com.mein.kraft.core.mapGenerator.{ Chunk, GameObject}


class Tree(size:Int=5,position: GameObject) extends TreeInterf {

  def this(size: Int, position: GameObject, chunk: Chunk) {
    this(size, position)
    this.chunk = chunk

  }

  /**
  * Generation by using a point as an axial column to generate a 4 block unit level
  */
  def unaryGeneration(e: Int): Unit = {

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord, position.yCoord, position.zCoord + 1 + e))

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord + 1, position.yCoord, position.zCoord + 1 + e))

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord, position.yCoord + 1, position.zCoord + 1 + e))

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord + 1, position.yCoord + 1, position.zCoord + 1 + e))

  }

  /**
  * Generation by using a point as an axial column to generate a 4 block unit position by
  * incrementing x axis and y axis by a defined value and incrementing by 1 each block each time
   */
  def binaryGeneration(u: Int, v: Int): Unit = {

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord + 1, position.yCoord + 1, position.zCoord + 1 + v))

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord + 1 + u, position.yCoord + 1, position.zCoord + 1 + v))

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord + 1, position.yCoord + 1 + u, position.zCoord + 1 + v))

    chunk.generateGlobal(new GameObject( new Model ,position.xCoord + 1 + u, position.yCoord + 1 + u, position.zCoord + 1 + v))

  }


  def triangleLfoliage(lFoliage: Int, trunkLimit: Int, size: Int): Unit = {

    for (u <- lFoliage until 0) {

      for (v <- (size - trunkLimit) to u) {

        binaryGeneration(u, v)
      }
    }

  }


  def squarreRandomLfoliage(lFoliage: Int, trunkLimit: Int, size: Int): Unit = {
    val k = new scala.util.Random

    for (u <- lFoliage until 0) {

      for (v <- (size - trunkLimit) to 0) {
        val k1 = k.nextInt(2)
        if (k1==0){
        binaryGeneration(u, v)}
      }
    }

  }


  def creation(): Unit = {

    val sizes = size
    val g = new scala.util.Random
    val trunkLimit = (sizes / 3) + g.nextInt((sizes / 2) - (sizes / 3) + 1)
    val lFoliage = g.nextInt((sizes / 5) + 1)

    for (e <- 0 until trunkLimit) {

      unaryGeneration(e)

    }

    val k = new scala.util.Random
    val k1 = k.nextInt(2)
    k1 match {
      case 0 => triangleLfoliage(lFoliage, trunkLimit, size)
      case 1 => squarreRandomLfoliage(lFoliage, trunkLimit, size)

    }
  }
}

