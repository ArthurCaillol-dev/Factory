package com.factory.game;

import com.factory.Screens.GameScreen;
import com.factory.Screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GameLoop extends Game {
    private MenuScreen MenuScreen;
    private GameScreen gameScreen;


	@Override
    public void create() {
        // Gdx.graphics.setWindowedMode(1920, 1080);
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        MenuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this, null);

        setScreen(new MenuScreen(this));
    }

    // Méthode pour passer à l'écran de jeu
    public void goToGameScreen() {
        setScreen(gameScreen);
    }

    // Méthode pour revenir au menu principal
    public void goToMenuScreen() {
        setScreen(MenuScreen);
    }
}