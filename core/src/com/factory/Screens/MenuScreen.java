package com.factory.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.factory.enemy.Difficulty;
import com.factory.game.GameLoop;


public class MenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private GameLoop game;
    private Music menuMusic;

    Image logo;
    TextButton startButton;
    TextButton optionsButton;
    TextButton exitButton;
    TextButton easy;
    TextButton normal;
    TextButton nightmare;
    TextButton hell;
    TextButton retourButton;
    ButtonUI buttonUI;

    Image background;

    private float volumeMusic;

    private final int buttonWidth = 200;
    private final int buttonHeight = 75;

    Table table;

    private Difficulty difficulty;

    public MenuScreen(GameLoop game) {
        this.game = game;
        background = new Image(new Texture(Gdx.files.internal("space.png")));
    }

    @Override
    public void show() {
        buttonUI = new ButtonUI();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        volumeMusic = 0.5f;
        skin = new Skin(Gdx.files.internal("skin-button/skins/visui/assets/uiskin.json"));
        logo = new Image(new Texture("logo.png"));


        buttonUI.optionsCreate();
        createUI();
        createMusic();
        createDifficultyUI();

        stage.addActor(background);
        stage.addActor(logo);
        stage.addActor(table);
        stage.addActor(easy);
        stage.addActor(normal);
        stage.addActor(nightmare);
        stage.addActor(hell);
        stage.addActor(retourButton);
        stage.addActor(buttonUI.optionsTable);
    }

    private void createMusic() {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("main_theme.mp3"));
        menuMusic.setLooping(true);
        menuMusic.setVolume(1f);
        menuMusic.play();
    }

    private void createUI() {
        logo.setPosition((int) Gdx.graphics.getWidth() / 2, (int) Gdx.graphics.getHeight() * 2 - 200);

        table = new Table();
        table.setFillParent(true);
        table.left();
        table.padLeft(250);
        table.padTop(50);

        startButton = new TextButton("Jouer", skin);
        table.add(startButton).width(200).height(buttonHeight).padBottom(30).row();

        optionsButton = new TextButton("Options", skin, "default");
        table.add(optionsButton).width(buttonWidth).height(buttonHeight).padBottom(30).row();

        exitButton = new TextButton("Quitter", skin, "default");
        table.add(exitButton).width(buttonWidth).height(buttonHeight).padBottom(30).row();

        buttonUI.optionsTable.setPosition((int) Gdx.graphics.getWidth() / 3, (int) Gdx.graphics.getHeight() / 2);
        buttonUI.optionsTable.padLeft(400);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                choiceDifficulty();
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                optionsMenu();
            }
        });

        buttonUI.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resume();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonUI.muteCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isMuted = buttonUI.muteCheckBox.isChecked();
                if (isMuted) {
                    menuMusic.setVolume(0.f);
                } else {
                    menuMusic.setVolume(volumeMusic);
                }
            }
        });

        buttonUI.screenSelectBox.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selectedSize = buttonUI.screenSelectBox.getSelected();
                if (selectedSize == "1280x720") {
                    Gdx.graphics.setWindowedMode(1280, 720);
                    resize(1280, 720);
                } else if (selectedSize == "800x600") {
                    Gdx.graphics.setWindowedMode(800, 600);
                    resize(800, 600);
                } else if (selectedSize == "Plein écran") {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                } else {
                    Gdx.graphics.setWindowedMode(1920, 1080);
                    resize(1920, 1080);
                }

            }
        });

        buttonUI.volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                volumeMusic = buttonUI.volumeSlider.getValue();
                menuMusic.setVolume(volumeMusic);
            }
        });
    }

    private void createDifficultyUI() {
        Vector2 position = new Vector2(250, Gdx.graphics.getHeight());
        easy = buttonUI.createButton("Facile", skin, position, false, - 250, 0);
        normal = buttonUI.createButton("Normal", skin, position, false, -250, 0);
        nightmare = buttonUI.createButton("Cauchemar", skin, position, false, - 250, 0);
        hell = buttonUI.createButton("Enfer", skin, position, false, 0, 0);
        retourButton = buttonUI.createButton("Back", skin, position, false, 0, 0);
        retourButton.setPosition(250, position.y + 105);
    }

    private void choiceDifficulty() {
        easy.setVisible(true);
        normal.setVisible(true);
        nightmare.setVisible(true);
        hell.setVisible(true);
        retourButton.setVisible(true);
        startButton.setVisible(false);
        optionsButton.setVisible(false);
        exitButton.setVisible(false);

        easy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Difficulty.setDifficulty(1);
                toGame();
            }
        });

        normal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Difficulty.setDifficulty(2);
                toGame();
            }
        });

        nightmare.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Difficulty.setDifficulty(3);
                toGame();
            }
        });

        hell.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Difficulty.setDifficulty(4);
                toGame();
            }
        });

        retourButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resume();
            }
        });
    }

    private void toGame() {
        game.setScreen(new GameScreen(game, difficulty));
        menuMusic.dispose();
    }

    private void optionsMenu() {
        startButton.setVisible(false);
        optionsButton.setVisible(false);
        exitButton.setVisible(false);
        buttonUI.backButton.setVisible(true);
        buttonUI.optionsTable.setVisible(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        Vector2 position = new Vector2(250, height / 2 - 60);
        stage.getViewport().update(width, height, true);

        easy.setPosition(position.x, position.y);
        normal.setPosition(position.x * 2, position.y);
        nightmare.setPosition(position.x * 3, position.y);
        hell.setPosition(position.x * 4, position.y);
        retourButton.setPosition(position.x, position.y + 105);
        logo.setPosition((int) (width / 5) - width / 6, (int) height - (height / 5));
        buttonUI.optionsTable.setPosition(width / 5, height / 3);
        table.setFillParent(true);
        table.left();
        table.padLeft(250);
        table.padTop(50);
    }

    @Override
    public void pause() {
        menuMusic.pause();
    }

    @Override
    public void resume() {
        startButton.setVisible(true);
        optionsButton.setVisible(true);
        exitButton.setVisible(true);
        buttonUI.backButton.setVisible(false);
        buttonUI.optionsTable.setVisible(false);
        easy.setVisible(false);
        normal.setVisible(false);
        nightmare.setVisible(false);
        hell.setVisible(false);
        retourButton.setVisible(false);

        menuMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        if (menuMusic != null) {
            menuMusic.dispose();
        }
    }
}
