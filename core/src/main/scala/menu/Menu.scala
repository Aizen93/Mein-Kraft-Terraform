package com.mein.kraft.core.menu

import com.badlogic.gdx.graphics._
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.g2d.{BitmapFont, Sprite, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, TextButton}
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.GameListener
import com.mein.kraft.core.ui.UserInterface
import menu.MenuSettings

class Menu(_game: MainScreen) extends Screen {
  var stage: Stage = _
  var start_button: TextButton = _
  var creative_button: TextButton = _
  var load_button: TextButton = _
  var settings_button: TextButton = _
  var quit_button: TextButton = _
  var textButtonStyle: TextButtonStyle = _
  var pixmap: Pixmap = _
  var font: BitmapFont = _
  var skin: Skin = _
  var buttonAtlas: TextureAtlas = _
  var background: Texture = _
  var title: Texture = _
  var backgroundSprite: Sprite = _

  val config = new Configurator("main.properties")
  var music_prop = config.getConfig("soundBackground")
  var sound_prop = config.getConfig("soundClick")
  var volume = config.getConfig("volume")
  var sound: Sound = Gdx.audio.newSound(Gdx.files.internal(sound_prop))
  var music: Music = Gdx.audio.newMusic(Gdx.files.internal(music_prop))

  var game: MainScreen = _game
  var camera: OrthographicCamera = new OrthographicCamera

  camera.setToOrtho(false, 800, 480)
  create()

  def create(): Unit = {
    var buttonOffset = 15
    stage = new Stage()

    background = new Texture(Gdx.files.internal("MeinKraftBackGround.jpg"))
    title = new Texture(Gdx.files.internal("Mein-Kraft.png"))
    music.play
    music.setVolume(volume.toFloat)
    music.setLooping(true)

    Gdx.input.setInputProcessor(stage)
    createSkin()
    start_button = new TextButton("Start Game", skin)
    start_button.setPosition(
      (Gdx.graphics.getWidth / 2) - (Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) + (start_button.getHeight + buttonOffset))
    start_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        println("Start Game")
        sound.play(volume.toFloat)
        game.setScreen(new GameListener)
        dispose()
      }
    })
    stage.addActor(start_button)

    creative_button = new TextButton("Creative Mode", skin)
    creative_button.setPosition(Gdx.graphics.getWidth / 2 - Gdx.graphics.getWidth / 8,
      Gdx.graphics.getHeight / 2)
    creative_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        sound.play(volume.toFloat)
        println("Creative Button")
      }
    })
    stage.addActor(creative_button)

    load_button = new TextButton("Load Game", skin)
    load_button.setPosition(
      (Gdx.graphics.getWidth / 2) - (Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) - (start_button.getHeight + buttonOffset))
    load_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        sound.play(volume.toFloat)
        println("Load Game")
      }
    })
    stage.addActor(load_button)

    settings_button = new TextButton("Settings", skin)
    settings_button.setPosition(
      (Gdx.graphics.getWidth / 2) - (Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) - (2 * (start_button.getHeight + buttonOffset)))
    settings_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        sound.play(volume.toFloat)
        music.dispose()
        game.setScreen(new MenuSettings(game))
        println("Settings")
      }
    })
    stage.addActor(settings_button)

    quit_button = new TextButton("Exit Game", skin)
    quit_button.setPosition(
      (Gdx.graphics.getWidth / 2) - (Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) - (2 * (start_button.getHeight + 4*buttonOffset)))
    quit_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        sound.play(volume.toFloat)
        Thread.sleep(300)
        println("Exit Game")
        Gdx.app.exit()
      }
    })
    stage.addActor(quit_button)
  }


  def createSkin(): Unit = {
    //font
    font = new BitmapFont()
    skin = new Skin()
    skin.add("default", font)

    //texture
    pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888)
    pixmap.setColor(Color.WHITE)
    pixmap.fill()
    skin.add("background", new Texture(pixmap))

    //button
    textButtonStyle = new TextButton.TextButtonStyle()
    textButtonStyle.up = skin.newDrawable("background", Color.GRAY)
    textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY)
    textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY)
    textButtonStyle.font = skin.getFont("default")
    skin.add("default", textButtonStyle)

  }

  override def render(delta: Float): Unit = {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    stage.act()
    stage.getBatch.begin
    stage.getBatch.draw(background, 0, 0,
      Gdx.graphics.getWidth, Gdx.graphics.getHeight)
    stage.getBatch.draw(title, Gdx.graphics.getWidth / 2 - title.getWidth / 2, Gdx.graphics.getHeight - title.getHeight - 20, title.getWidth, title.getHeight)
    stage.getBatch.end
    stage.draw()
  }

  override def dispose(): Unit = {
    stage.dispose()
  }

  override def resume(): Unit = {}

  override def resize(width: Int, height: Int): Unit = {}

  override def pause(): Unit = {}

  override def show(): Unit = {}

  override def hide(): Unit = {}
}
