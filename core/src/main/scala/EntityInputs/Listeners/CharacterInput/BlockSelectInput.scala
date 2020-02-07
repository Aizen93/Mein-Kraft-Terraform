package com.mein.kraft.core.Inputs.Listeners.PlayerListeners

import com.badlogic.gdx.InputAdapter
import com.mein.kraft.core.mapGenerator.Block.TextureBuilder

class BlockSelectInput(texture: TextureBuilder) extends InputAdapter{

  var selectedBlock = "grass"
  var count = 0

  /**
    * Uses the mouse scroll to iterate through our list of textures (12 total)
    * @param amount
    * @return
    */
  override def scrolled(amount: Int): Boolean = {
    count += amount
    if (count < 0) {
      count = 12
    }
    else if (count > 12) {
      count = 0
    }
    texture.selectMaterial(count)
    selectedBlock = texture.selectedBlockTextInfo
    true
  }
}
