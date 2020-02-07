package menu

import com.badlogic.gdx.assets.loaders.AssetLoader
import com.badlogic.gdx.{Gdx, Input, Screen}
import com.badlogic.gdx.audio.{Music, Sound}
import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.badlogic.gdx.graphics.g2d.{Batch, BitmapFont, Sprite, TextureAtlas}
import com.badlogic.gdx.graphics._
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, Slider, TextButton, TextField}
import com.badlogic.gdx.scenes.scene2d.{Event, EventListener, InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mein.kraft.core.GameListener
import com.mein.kraft.core.configurator.Configurator
import com.mein.kraft.core.menu.MainScreen

class MenuSettings(_game: MainScreen) extends Screen {
  var stage: Stage = _
  var fs_button: TextButton = _
  var upvol_button: TextButton = _
  var downvol_button: TextButton = _
  var menu_button: TextButton = _
  var textButtonStyle: TextButtonStyle = _
  var pixmap: Pixmap = _
  var font: BitmapFont = _
  var skin: Skin = _
  var buttonAtlas: TextureAtlas = _
  var background: Texture = _
  var title: Texture = _
  var backgroundSprite: Sprite = _
  var fs_bool = false
  var input_UP: TextField = _
  var input_LEFT: TextField = _
  var input_BACK: TextField = _
  var input_RIGHT: TextField = _
  var textfStyle: TextFieldStyle = _
  var input_valueUP: Int = _
  var input_valueLEFT: Int = _
  var input_valueBACK: Int = _
  var input_valueRIGHT: Int = _

  val config = new Configurator("main.properties")
  var music_prop = config.getConfig("soundBackground")
  var sound_prop = config.getConfig("soundClick")

  var sound: Sound = Gdx.audio.newSound(Gdx.files.internal(sound_prop))
  var music: Music = Gdx.audio.newMusic(Gdx.files.internal(music_prop))
  var volume:Float = config.getConfig("volume").toFloat

  var game: MainScreen = _game
  var camera: OrthographicCamera = new OrthographicCamera

  camera.setToOrtho(false, 800, 480)
  create()

  def create(): Unit = {
    var buttonOffset = 15
    stage = new Stage()

    background = new Texture(Gdx.files.internal("LoadingBackground.jpg"))
    title = new Texture(Gdx.files.internal("Mein-Kraft.png"))
    music.play
    music.setVolume(volume)
    music.setLooping(true)

    Gdx.input.setInputProcessor(stage)
    createSkin()

    fs_button = new TextButton("Full Screen", skin)
    fs_button.setPosition(
      (Gdx.graphics.getWidth / 2) - (Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) + (fs_button.getHeight + buttonOffset))
    fs_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        println("Full Screen")
        fs_bool = !fs_bool
        config.setConfig("fullscreen_mode", fs_bool.toString)
        sound.play(volume)
        if(fs_bool){
          Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode())
        }


      }
    })
    stage.addActor(fs_button)

    upvol_button = new TextButton("Up Volume", skin)
    upvol_button.setPosition(
      Gdx.graphics.getWidth / 2 - 2*(Gdx.graphics.getWidth / 8),
      Gdx.graphics.getHeight / 2)
    upvol_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        sound.play(volume)
        if(volume <= 1.0f) {
          volume = (volume +0.1f)
          config.setConfig("volume", volume.toString)
          if(volume > 1.0f) {
            volume = 1f
            config.setConfig("volume", volume.toString)
          }
        }
        music.setVolume(volume)
        Gdx.input.setInputProcessor(stage)
        println("up volume: "+ volume)
      }
    })
    stage.addActor(upvol_button)

    downvol_button = new TextButton("Down Volume", skin)
    downvol_button.setPosition(
      Gdx.graphics.getWidth / 2 + buttonOffset,
      Gdx.graphics.getHeight / 2)
    downvol_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        sound.play(volume)
        if(volume <= 1.0f) {
          volume = (volume - 0.1f)
          config.setConfig("volume", volume.toString)
          if(volume < 0f) {
            volume = 0f
            config.setConfig("volume", volume.toString)
          }
        }
        music.setVolume(volume)
        Gdx.input.setInputProcessor(stage)
        println("down volume: "+ volume)
      }
    })
    stage.addActor(downvol_button)



    textfStyle = new TextFieldStyle()
    textfStyle.font = new BitmapFont()
    textfStyle.fontColor = Color.RED

    input_UP = new TextField("", textfStyle)
    input_UP.setMessageText("SELECT FORWARD MOVEMENT KEY")
    input_UP.setWidth(300)
    input_UP.setHeight(80)
    input_UP.setPosition(
      (Gdx.graphics.getWidth / 2) - 2*(Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) - (fs_button.getHeight + buttonOffset))
    stage.addActor(input_UP)

    input_BACK = new TextField("", textfStyle)
    input_BACK.setMessageText("SELECT BACKWARD MOVEMENT KEY")
    input_BACK.setWidth(300)
    input_BACK.setHeight(80)
    input_BACK.setPosition(
      (Gdx.graphics.getWidth / 2) - 2*(Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) - (2 * (fs_button.getHeight + buttonOffset)))
    stage.addActor(input_BACK)

    input_LEFT = new TextField("", textfStyle)
    input_LEFT.setMessageText("SELECT LEFT MOVEMENT KEY")
    input_LEFT.setWidth(300)
    input_LEFT.setHeight(80)
    input_LEFT.setPosition(
      (Gdx.graphics.getWidth / 2) + buttonOffset,
      (Gdx.graphics.getHeight / 2) - (2 * (fs_button.getHeight + buttonOffset)))
    stage.addActor(input_LEFT)

    input_RIGHT = new TextField("", textfStyle)
    input_RIGHT.setMessageText("SELECT RIGHT MOVEMENT KEY")
    input_RIGHT.setWidth(300)
    input_RIGHT.setHeight(80)
    input_RIGHT.setPosition(
      (Gdx.graphics.getWidth / 2) + buttonOffset,
      (Gdx.graphics.getHeight / 2) - (fs_button.getHeight + buttonOffset))
    stage.addActor(input_RIGHT)






    menu_button = new TextButton("Menu", skin)
    menu_button.setPosition(
      (Gdx.graphics.getWidth / 2) - (Gdx.graphics.getWidth / 8),
      (Gdx.graphics.getHeight / 2) - (2 * (fs_button.getHeight + 4*buttonOffset)))
    menu_button.addListener(new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
        sound.play(volume)

        //DOESN'T WORK: VALUES NOT UPDATED
        input_valueUP = Input.Keys.valueOf(input_UP.getMessageText)
        config.setConfig("go_forward", String.valueOf(input_valueUP))

        input_valueBACK = Input.Keys.valueOf(input_BACK.toString.charAt(0).toString)
        config.setConfig("go_backward", String.valueOf(input_valueBACK))

        input_valueLEFT = Input.Keys.valueOf(input_LEFT.toString.charAt(0).toString)
        config.setConfig("go_left", String.valueOf(input_valueLEFT))

        input_valueRIGHT = Input.Keys.valueOf(input_RIGHT.toString.charAt(0).toString)
        config.setConfig("go_right", String.valueOf(input_valueRIGHT))


        game.setScreen(new com.mein.kraft.core.menu.Menu(game))
        dispose()
      }
    })
    stage.addActor(menu_button)



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
