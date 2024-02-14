package com.factory.InputMultiplexer;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public class InputMultiplexer implements InputProcessor {

    private Array<InputProcessor> processors = new Array<>();

    public void addProcessor(InputProcessor processor) {
        processors.add(processor);
    }

    public void removeProcessor(InputProcessor processor) {
        processors.removeValue(processor, true);
    }

    @Override
    public boolean keyTyped(char character) {
        for (InputProcessor processor : processors) {
            if (processor.keyTyped(character)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor processor : processors) {
            if (processor.touchDown(screenX, screenY, pointer, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor processor : processors) {
            if (processor.touchUp(screenX, screenY, pointer, button)) {
                return true;
            }
        }
        return false;
    }

    // ... Les autres méthodes à mettre à jour de manière similaire

    @Override
    public boolean keyDown(int keycode) {
        for (InputProcessor processor : processors) {
            if (processor.keyDown(keycode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (InputProcessor processor : processors) {
            if (processor.keyUp(keycode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor processor : processors) {
            if (processor.touchCancelled(screenX, screenY, pointer, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (InputProcessor processor : processors) {
            if (processor.touchDragged(screenX, screenY, pointer)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (InputProcessor processor : processors) {
            if (processor.mouseMoved(screenX, screenY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        for (InputProcessor processor : processors) {
            if (processor.scrolled(amountX, amountY)) {
                return true;
            }
        }
        return false;
    }

}
