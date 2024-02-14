package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.factory.game.GameLoop;

public class DesktopLauncher {

    public Lwjgl3Application instance;

    private static void createConfig(Lwjgl3ApplicationConfiguration config) {
        config.setTitle("Factory");
        config.setForegroundFPS(60);
        config.useVsync(true);
    }

    public DesktopLauncher() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        createConfig(config);
        instance = new Lwjgl3Application(new GameLoop(), config);
    }

    public static void main(String[] arg) {
        new DesktopLauncher();
    }
}
