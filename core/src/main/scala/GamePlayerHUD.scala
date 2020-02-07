package com.mein.kraft.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class GamePlayerHUD {
  var batch: SpriteBatch = new SpriteBatch()
  val aimingCross: Texture = new Texture(Gdx.files.internal("aimingCross/cross32.png"))

  def updateCrossHair(): Unit = {
    batch.begin()
    batch.draw(aimingCross, (Gdx.graphics.getWidth / 2) - (aimingCross.getWidth / 2), (Gdx.graphics.getHeight / 2) - (aimingCross.getHeight / 2))
    batch.end()
  }
}
