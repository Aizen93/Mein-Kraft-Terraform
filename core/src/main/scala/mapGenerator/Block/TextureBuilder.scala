package com.mein.kraft.core.mapGenerator.Block

import com.badlogic.gdx.graphics.g3d.{Material, Model}
import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.mapGenerator.GameObject

class TextureBuilder extends ITextureKraft {
  val config = new Configurator("main.properties")

  var grassBlockModel : Model = _

  /*---------------------Asset Manager --------------------*/
  assets.load(config.getConfig("grass_block"), classOf[Model])
  assets.finishLoading()
  grassBlockModel = assets.get(config.getConfig("grass_block"), classOf[Model])
  /*-------------------------------------------------------*/

  var selectedMaterial : Material = grassMaterial
  var selectedBlockTextInfo : String = "grass"

  def selectMaterial(material : String) : Unit = {
      material match {
          case "grass"      => selectedMaterial = grassMaterial
          case "sand"       => selectedMaterial = sandMaterial
          case "stone"      => selectedMaterial = stoneMaterial
          case "diamond"    => selectedMaterial = diamondMaterial
          case "gravel"     => selectedMaterial = gravelMaterial
          case "dirt"       => selectedMaterial = dirtMaterial
          case "cobble"     => selectedMaterial = cobbleMaterial
          case "brick"      => selectedMaterial = brickMaterial
          case "plank"      => selectedMaterial = plankMaterial
          case "stonebrick" => selectedMaterial = stoneBrickMaterial
          case "bedrock"    => selectedMaterial = bedRockMaterial
          case "tronktree"  => selectedMaterial = tronkTreeMaterial
          case "void"       => selectedMaterial = voidMaterial
          case _            => selectedMaterial = grassMaterial
      }
  }

  def selectMaterial(material : Int) : Unit = {
      material match {
          case 0  => selectedMaterial = grassMaterial; selectedBlockTextInfo = "grass"
          case 1  => selectedMaterial = sandMaterial; selectedBlockTextInfo = "sand"
          case 2  => selectedMaterial = brickMaterial; selectedBlockTextInfo = "brick"
          case 3  => selectedMaterial = diamondMaterial; selectedBlockTextInfo = "diamond"
          case 4  => selectedMaterial = stoneMaterial; selectedBlockTextInfo = "stone"
          case 5  => selectedMaterial = dirtMaterial; selectedBlockTextInfo = "dirt"
          case 6  => selectedMaterial = plankMaterial; selectedBlockTextInfo = "plank"
          case 7  => selectedMaterial = tronkTreeMaterial; selectedBlockTextInfo = "tronk tree"
          case 8  => selectedMaterial = cobbleMaterial; selectedBlockTextInfo = "cobble"
          case 9  => selectedMaterial = gravelMaterial; selectedBlockTextInfo = "gravel"
          case 10 => selectedMaterial = stoneBrickMaterial; selectedBlockTextInfo = "stone brick"
          case 11 => selectedMaterial = bedRockMaterial; selectedBlockTextInfo = "bed rock"
          case 12 => selectedMaterial = voidMaterial; selectedBlockTextInfo = "Void"
          case default => selectedMaterial = grassMaterial
      }
  }

  def applyMaterial() : Unit = {
      grassBlockModel.materials.get(0).clear()
      grassBlockModel.materials.get(0).set(selectedMaterial)

  }

  def setMaterialTo(obj : GameObject) : Unit = {
    obj.materials.get(0).clear()
    obj.materials.get(0).set(selectedMaterial)
  }


  def getblockModel() : Model = {
      grassBlockModel
  }

}
