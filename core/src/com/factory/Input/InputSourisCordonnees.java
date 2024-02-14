package com.factory.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;



public class InputSourisCordonnees implements InputProcessor {
    private OrthographicCamera camera;

    public InputSourisCordonnees(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Vector2 processMouseCoordinates(int screenX, int screenY) {
        float invertedY = Gdx.graphics.getHeight() - screenY;
        Vector3 worldCoordinates = new Vector3(screenX, invertedY, 0);
        camera.unproject(worldCoordinates);
        return new Vector2(worldCoordinates.x, worldCoordinates.y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return true;
    }
}
