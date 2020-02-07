package com.mein.kraft.core.objectGenerator

import com.badlogic.gdx.graphics.g3d.Model
import com.mein.kraft.core.mapGenerator.{ Chunk, GameObject}

class House(width:Int=5,position: GameObject) extends HouseInterf {
  def this(height:Int,width:Int ,length: Int, position: GameObject, chunk: Chunk) {
    this(width,position)
    this.length=length
    this.height=height
    this.chunk=chunk
  }

  def wallX (factor:Int):Unit={
    for (u <- position.xCoord to position.xCoord +length) {
      for (v <- position.zCoord to position.zCoord+height ) {
        chunk.generateGlobal(new GameObject( new Model ,u, position.yCoord+factor*width, v))
      }
      }
  }

  def wallY (factor:Int):Unit= {
    for (u <- position.yCoord to position.yCoord + width) {
      for (v <- position.zCoord to position.zCoord + height) {
        chunk.generateGlobal(new GameObject( new Model ,position.xCoord + factor * length, u, v))
        }
      }
    }


  def roofPlate():Unit={
     for (u <- position.xCoord to position.xCoord + length) {
     for (v <- position.yCoord to position.yCoord + width) {
         chunk.generateGlobal(new GameObject( new Model , u, v,position.zCoord+1+height))
       }
     }
   }

// AR parite centre +exterieur
  def rootTriangle():Unit= {
    var e: Int = 0
    var h: Int = 0

    if (width < length) {
      for (t <- 0 to (width / 2).toInt) {
        for (u <- position.xCoord to position.xCoord + length) {
          chunk.generateGlobal(new GameObject( new Model ,u, position.yCoord + e, position.zCoord + 1 + height + e))
          chunk.generateGlobal(new GameObject( new Model ,u, position.yCoord - e + width, position.zCoord + 1 + height + e))
          e = e + 1
        }
      }
      for (j <- 0 until (e)) {
        for (f <- 0 until (length)) {

          if (f == j && f != 0) {
            chunk.generateGlobal(new GameObject( new Model ,f, position.yCoord, position.zCoord + 1 + height + j))
            chunk.generateGlobal(new GameObject( new Model ,f, position.yCoord+width, position.zCoord + 1 + height + j))
          }
        }
      }
    }
      else
      {
        for (t <- 0 to (length / 2).toInt) {
          for (u <- position.yCoord to position.yCoord + width) {
            chunk.generateGlobal(new GameObject( new Model ,position.xCoord+e,u, position.zCoord + 1 + height+e))
            chunk.generateGlobal(new GameObject( new Model ,position.xCoord-e+width, u , position.zCoord + 1 + height+e))
            e = e + 1
          }
        }

        for (j <- 0 until (e)) {
          for (f <- 0 until (width)) {

            if (f == j && f != 0) {
              chunk.generateGlobal(new GameObject( new Model ,position.xCoord, f, position.zCoord + 1 + height + j))
              chunk.generateGlobal(new GameObject( new Model ,position.xCoord+length, f, position.zCoord + 1 + height + j))
            }
          }
        }
      }
    }

  def creation(): Unit = {
    wallX(0)
    wallX(1)

    wallY(0)
    wallY(1)
    val k = new scala.util.Random
    val k1 = k.nextInt(2)
    k1 match {
      case 0 => roofPlate()
      case 1 => rootTriangle()
    }
  }


}
