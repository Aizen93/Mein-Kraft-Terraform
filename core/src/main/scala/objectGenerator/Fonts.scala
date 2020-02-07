package com.mein.kraft.core.objectGenerator

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont

class Fonts {

  val kraft = new BitmapFont(Gdx.files.internal("fonts/MeinKraft.fnt"))
  val doted = new BitmapFont(Gdx.files.internal("fonts/doted.fnt"))
  val mario = new BitmapFont(Gdx.files.internal("fonts/Mario.fnt"))

  def getMarioFont(size : Float) : BitmapFont = {
      mario.getData.setScale(size)
      return mario

  }

  def getDotedFont(size : Float) : BitmapFont = {
      doted.getData.setScale(size)
      return doted
  }

  def getKraftFont(size : Float) : BitmapFont = {
      kraft.getData.setScale(size)
      return kraft
  }

}
