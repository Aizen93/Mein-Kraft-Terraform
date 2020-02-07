package com.mein.kraft.core.Controler

import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight

class LightControler {

  var lights: Environment = _
  var sky : Environment = _
  var lr = 0.1f; var lg = 0.1f; var lb = 0.1f
  var fr = 0.6f; var fg = 0.6f; var fb = 0.6f
  initLight()

  def initLight(): Unit = {
      lights = new Environment()
      lights.set(new ColorAttribute(ColorAttribute.Fog, fr, fg, fb, 0.2f))
      lights.set(new ColorAttribute(ColorAttribute.AmbientLight, lr, lg, lb, 1f))
      lights.add(new DirectionalLight().set(lr, lg, lb, 1f, -0.8f, -0.2f))

      sky = new Environment
      sky.set(new ColorAttribute(ColorAttribute.AmbientLight, lr, lg, lb, 1f))
      sky.add(new DirectionalLight().set(lr, lg, lb, 1f, -0.8f, -0.2f))
  }

  def updateLight() : Unit = {
      lights.clear()
      lights.set(new ColorAttribute(ColorAttribute.Fog, fr, fg, fb, 0.2f))
      lights.set(new ColorAttribute(ColorAttribute.AmbientLight, lr, lg, lb, 1f))
      lights.add(new DirectionalLight().set(lr, lg, lb, 1f, -0.8f, -0.2f))

      sky.clear()
      sky.set(new ColorAttribute(ColorAttribute.AmbientLight, lr, lg, lb, 1f))
      sky.add(new DirectionalLight().set(lr, lg, lb, -1f, -0.8f, -0.2f))
  }

}
