package com.factory.Input;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;
import com.factory.Screens.MapScreen;

public class GestInput extends InputAdapter implements ApplicationListener{

    Array<Integer> keysPressed = new Array<>();
    InputMultiplexer multiplexer = new InputMultiplexer();


    @Override
    public boolean keyDown(int keycode) {
        if (!keysPressed.contains(keycode, false)) {
            keysPressed.add(keycode);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysPressed.removeValue(keycode, false);
        return true;
    }

    public void updateCameraMovement() {
        int speed = 1;

        for (int keycode : keysPressed) {
            switch (keycode) {
                case Keys.LEFT:
                    MapScreen.instance.translateCamera(-speed, 0);
                    break;
                case Keys.RIGHT:
                MapScreen.instance.translateCamera(speed, 0);
                    break;
                case Keys.UP:
                MapScreen.instance.translateCamera(0, speed);
                break;
                case Keys.DOWN:
                MapScreen.instance.translateCamera(0, -speed);
                break;
                default:
                break;
            }
        }
    }

    @Override
    public void create() {
        multiplexer.addProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        this.dispose();
    }
}
