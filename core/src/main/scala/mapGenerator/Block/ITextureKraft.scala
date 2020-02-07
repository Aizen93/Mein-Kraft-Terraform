package com.mein.kraft.core.mapGenerator.Block

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute
import com.badlogic.gdx.graphics.g3d.{Material, Model}
import com.mein.kraft.core.mapGenerator.GameObject

trait ITextureKraft {
  var assets: AssetManager = new AssetManager()

  private val textureSand = new Texture(Gdx.files.internal("block/Sand/sand.png"), true)
  private val textureStone = new Texture(Gdx.files.internal("block/Stone/stone.jpg"), true)
  private val textureDiamond = new Texture(Gdx.files.internal("block/Diamond/diamond.png"), true)
  private val textureGravel = new Texture(Gdx.files.internal("block/Gravel/gravel.jpg"), true)
  private val textureDirt = new Texture(Gdx.files.internal("block/Dirt/dirt.png"), true)
  private val textureCobble = new Texture(Gdx.files.internal("block/CobbleStone/cobblestoneg.jpg"), true)
  private val textureBrick = new Texture(Gdx.files.internal("block/Brick/brick.jpg"), true)
  private val texturePlank = new Texture(Gdx.files.internal("block/Plank/plank.jpg"), true)
  private val textureTronkTree = new Texture(Gdx.files.internal("block/Plank/tronk_tree.png"), true)
  private val textureGrass = new Texture(Gdx.files.internal("block/Grass_Block_TEX.png"), true)
  private val textureBedRock = new Texture(Gdx.files.internal("block/BedRock/bedrock.png"), true)
  private val textureStoneBrick = new Texture(Gdx.files.internal("block/StoneBrick/stonebrickh.png"), true)
  private val textureVoid = new Texture(Gdx.files.internal("block/Void/void.jpg"), true)

  val sandMaterial = new Material(TextureAttribute.createDiffuse(textureSand))
  val stoneMaterial = new Material(TextureAttribute.createDiffuse(textureStone))
  val diamondMaterial = new Material(TextureAttribute.createDiffuse(textureDiamond))
  val gravelMaterial = new Material(TextureAttribute.createDiffuse(textureGravel))
  val dirtMaterial = new Material(TextureAttribute.createDiffuse(textureDirt))
  val cobbleMaterial = new Material(TextureAttribute.createDiffuse(textureCobble))
  val brickMaterial = new Material(TextureAttribute.createDiffuse(textureBrick))
  val plankMaterial = new Material(TextureAttribute.createDiffuse(texturePlank))
  val tronkTreeMaterial = new Material(TextureAttribute.createDiffuse(textureTronkTree))
  val grassMaterial = new Material(TextureAttribute.createDiffuse(textureGrass))
  val bedRockMaterial = new Material(TextureAttribute.createDiffuse(textureBedRock))
  val stoneBrickMaterial = new Material(TextureAttribute.createDiffuse(textureStoneBrick))
  val voidMaterial = new Material(TextureAttribute.createDiffuse(textureVoid))

  /**
    * Current material selected to be applied on the model block
    */
  var selectedMaterial : Material

  /**
    * Selects a material to be applied for our model from the list of textures we have
    * @param material
    */
  def selectMaterial(material : String) : Unit
  def selectMaterial(material : Int) : Unit

  /**
    * Applies the material selected to the model
    */
  def applyMaterial() : Unit

  /**
    * returns the model textured with current selected material
    * @return Model
    */
  def getblockModel() : Model

  /**
    * Takes a GameObject and apply selected texture to it
    * @param obj GameObject instance
    */
  def setMaterialTo(obj : GameObject) : Unit
}
