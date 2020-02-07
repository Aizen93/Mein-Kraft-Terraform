package com.mein.kraft.core.ui

import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{Container, Label, ProgressBar, Skin, 
                                           Stack, Table, Window}
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle
import com.badlogic.gdx.utils.Align

trait UserInterface extends Screen {
  val uiStage = new Stage
  val skin = new Skin(Gdx.files.internal("craftacular-ui.json"))
  val root = new Table(skin)

  val armorBar = createProgressBar("armor", "armor", "armor-bg", 0)
  val healthBar = createProgressBar("health", "heart", "heart-bg", 10)
  val hungerBar = createProgressBar("hunger", "meat", "meat-bg", 10)
  val xpBar = createProgressBar("xp", "xp", "xp-bg", 0)
  val xpLabel = new Label("0", skin, "xp")
  val hotbar = new Window("", skin)

  uiStage.addActor(root)

  //root.setDebug(true) //debug

  root.setFillParent(true)

  root.add.colspan(2).expandY.padTop(100)
  root.row
  root.add(armorBar).width(18 * 10).height(18).padBottom(10)
  root.row
  root.add(healthBar).width(18 * 10).height(18)
  root.add(hungerBar).width(18 * 10).height(18).padLeft(15)
  root.row
  root.add(xpLabel).colspan(2)
  xpLabel.setAlignment(Align.center)
  root.row
  root.add(xpBar).width(378).height(9).colspan(2)
  root.row
  hotbar.left.padLeft(15)
  
  val hotbarTable = new Table
  for(x <- 0 until 10) {
    val container = new Container
    container.setBackground(skin.getDrawable("cell"))
    hotbarTable.add(container).size(50)
  }
  hotbar.add(hotbarTable)
  root.add(hotbar).colspan(2).padTop(10)

  def createProgressBar(name: String, fg: String, bg: String, 
                        initValue: Int) : ProgressBar = {
    val style = skin.get(name, classOf[ProgressBarStyle])
    val fgTDraw = skin.getTiledDrawable(fg)
    val bgTDraw = skin.getTiledDrawable(bg)

    fgTDraw.setMinWidth(0)
    bgTDraw.setMinWidth(0)
    style.knobBefore = fgTDraw
    style.background = bgTDraw

    val progressBar = new ProgressBar(0, 10, 1, false, style)

    progressBar.setValue(initValue)
    progressBar
  }

  abstract override def render(delta: Float) : Unit = {
    super.render(delta)

    uiStage.act(Gdx.graphics.getDeltaTime)
    uiStage.draw
  }

  abstract override def dispose : Unit = {
    super.dispose

    uiStage.dispose
    skin.dispose
  }

  abstract override def resize(width: Int, height: Int) : Unit = {
    super.resize(width, height)

    uiStage.getViewport.update(width, height, true)
  }
}
