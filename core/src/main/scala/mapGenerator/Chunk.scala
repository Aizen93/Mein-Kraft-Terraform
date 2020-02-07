package com.mein.kraft.core.mapGenerator

import com.badlogic.gdx.graphics.g3d.Model

/** Unit of the map that is used to generate parts of it
 */
class Chunk() extends ChunkInterf{

  //<editor-fold="Params">

  def this(length: Int , width: Int , height: Int) = {

    this()
    this.length=length
    this.width=width
    this.height=height
  }

  def storeInBlockSet(block: GameObject, x: Int, y: Int, z: Int): Unit = {
    this.blockSet(x)(y)(z) = block
  }

  def blockSetSet(value :GameObject ): Unit = {

    this.blockSet(value.xCoord)(value.yCoord)(value.zCoord).zCoord=value.zCoord
    this.blockSet(value.xCoord)(value.yCoord)(value.zCoord).yCoord=value.yCoord
    this.blockSet(value.xCoord)(value.yCoord)(value.zCoord).xCoord=value.xCoord

  }

  def generateBlock(value : GameObject  ): Unit = {

    this.blockSet(value.xCoord)(value.yCoord)(value.zCoord)=new GameObject (new Model)

  }

  def generateGlobal(value :GameObject  ): Unit = {
    blockSetSet(value )
    generateBlock(value )
  }

  def getGroundLevel(x: Int, y: Int): Int ={
    var z: Int = 0
    while(blockSet(x)(y)(z)!=null){
      z += 1
    }
    z+1
  }
}
