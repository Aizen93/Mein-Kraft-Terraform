package com.mein.kraft.core.Controler

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.{Material, Model, ModelInstance}
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute
import com.badlogic.gdx.utils.Timer

class TimeCycleController(light : LightControler) extends ITimeCycle[ModelInstance] {
  private val atmosphere = light
  private val textureDay = new Texture(Gdx.files.internal("skybox/sky_photo3.jpg"), true)
  private val dayMaterial = new Material(TextureAttribute.createDiffuse(textureDay))

  private var state = 0
  private var lock = false
  private val accelerate = 1
  var seconds = 0
  var minutes = 0
  var hours = 0
  var reverse = 0.0075f
  val revfog = 0.01f

  override val skybox: ModelInstance = initSkybox
  skybox.materials.get(0).clear()
  skybox.materials.get(0).set(dayMaterial)

  /**
   * transition Jour ---> Nuit ---> Jour ---> Nuit ---> etc
   */
  override val timer: Timer.Task = Timer.schedule(() => {
    seconds += 1 * accelerate
    if (atmosphere.fr <= 0.2f) {
      atmosphere.fr += revfog;
      atmosphere.fg += revfog;
      atmosphere.fb += revfog
    } else if (atmosphere.fr >= 0.7f) {
      atmosphere.fr -= revfog;
      atmosphere.fg -= revfog;
      atmosphere.fb -= revfog
    }
    if (!lock) {
      atmosphere.lr += reverse * accelerate;
      atmosphere.lg += reverse * accelerate;
      atmosphere.lb += reverse * accelerate
    }
    light.updateLight()

    if (seconds == 60) {
      minutes += 1
      seconds = 0
      state += 1
      if (state == 2) {
        if (!lock) {
          lock = !lock
          state = 0
        } else {
          reverse = -reverse
          state = 0
          lock = !lock
        }
      }
      if (minutes == 60) {
        hours += 1
        minutes = 0
      }
    }

  }, 0.5f, 1)

  def initSkybox: ModelInstance = {
    val assets: AssetManager = new AssetManager
    assets.load("skybox/spacesphere.obj", classOf[Model])
    assets.finishLoading()

    val skybox = new ModelInstance(assets.get("skybox/spacesphere.obj", classOf[Model]))
    skybox
  }
}
