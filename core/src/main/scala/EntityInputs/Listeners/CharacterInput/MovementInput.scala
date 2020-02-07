package com.mein.kraft.core.Inputs.Listeners.PlayerListeners

import com.badlogic.gdx.Gdx
import com.mein.kraft.core.Inputs.ICharacterMovementInput
import com.mein.kraft.core.configurator.Configurator

class MovementInput() extends AnyRef with ICharacterMovementInput{

    val config = new Configurator("main.properties")

    def readMovementInput: Unit = {
        _frontSpeed = 0; _backSpeed = 0
        _leftSpeed = 0; _rightSpeed = 0;
        _boost = 0; _crouch = 0; _jump = 0

        if(Gdx.input.isKeyPressed(config.getConfig("go_forward").toInt)) {
            if (Gdx.input.isKeyPressed(config.getConfig("speed_up").toInt)) {
                _boost = 1
                _frontSpeed = 1
            }else {
                _frontSpeed = 1
                _boost = 0
            }
        }

        if(Gdx.input.isKeyPressed(config.getConfig("go_backward").toInt)){
            _backSpeed = 1
        }

        if(Gdx.input.isKeyPressed(config.getConfig("go_left").toInt)){
            _leftSpeed = 1
        }

        if(Gdx.input.isKeyPressed(config.getConfig("jump").toInt)){
            _jump = 1
        }

        if(Gdx.input.isKeyPressed(config.getConfig("crouch").toInt)){
            _crouch = 1
        }

        if(Gdx.input.isKeyPressed(config.getConfig("go_right").toInt)){
            _rightSpeed = 1
        }

    }

}
