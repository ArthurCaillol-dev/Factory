package com.factory.Screens;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.factory.Input.*;
import com.factory.batiment.*;
import com.factory.game.GameLoop;

public class UserInterface extends Game{
    public ScreenViewport viewport;
    public Stage stage;
    public GameLoop game;
    public InputMultiplexer multiplexer;
    private static Skin skin;
    private ButtonUI buttonInterface;
    public SpriteBatch spriteBatch;
    public Base base;
    public Batiment batiment;
    public Tourelle tourellesList;
    public Foreuse foreusesList;
    public BatimentRenderer batimentRenderer;
    public InventoryUI inventory;
    private ImageButton noyauButton;
    private Texture noyauTexture;
    private ImageButtonStyle noyauStyle;
    private ImageButton tourelleButton;
    private Texture tourelleTexture;
    private ImageButtonStyle tourelleStyle;
    private ImageButton ressourcesButton;
    private Texture ressourcesTexture;
    private ImageButtonStyle ressourcesStyle;
    public Table table;
    public GestInput input;
    public boolean isClickedBase;
    public boolean isClickedTourelle;
    public boolean isClickedRessource;
    public boolean isBuild;
    public boolean isCreatedTourelle;
    public boolean isCreatedForeuse;
    private boolean isUniqueTourelle;
    private boolean isUniqueForeuse;
    private String clickedThis;
    private int numero;
    private Set<Integer> numeroUsed;
    private Table tableOver;
    private Table tableWin;

    public InputSourisCordonnees inputSourisCordonnees;

    private final float buttonWidth = 200;
    private final float buttonHeight = 75;
    private final float padding = 10;

    private TiledMap tiledGameMap;
    private TiledMapTileLayer mapLayer;
    private int waterTile = 197;
    public float volumeMusic;

    private Music gameMusic;

    public InputMultiplexer getMultiplexer() {
        return multiplexer;
    }

    @Override
    public void create() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skin-button/skins/visui/assets/uiskin.json"));
        multiplexer = new InputMultiplexer();
        buttonInterface =  new ButtonUI();
        spriteBatch = new SpriteBatch();
        tourellesList = new Tourelle("Test", 0, 0, 0);
        foreusesList = new Foreuse("Foreuse", 0);
        batimentRenderer = new BatimentRenderer(spriteBatch);
        inventory = new InventoryUI();
        table = new Table();
        input = new GestInput();
        // base = new Base("test", 0);
        isClickedBase = false;
        isClickedRessource = false;
        isClickedTourelle = false;
        isCreatedTourelle = false;
        isCreatedForeuse = false;
        isUniqueTourelle = false;
        isUniqueForeuse = false;
        isBuild = false;
        numero = 0;
        numeroUsed = new HashSet<>();
        tiledGameMap = new TmxMapLoader().load("map/mapSprite.tmx");
        mapLayer = (TiledMapTileLayer) tiledGameMap.getLayers().get("Calque de Tuiles 1");
        volumeMusic = 0.5f;
        createMusic();

        buttonInterface.buttonCreate();
        batimentButtonCreate();
        tableCreate();
        placeBatiemnt();
        buttonEvent();
        createGameOver();
        createWin();

        stage.addActor(buttonInterface.pauseButton);
        stage.addActor(buttonInterface.resumeButton);
        stage.addActor(buttonInterface.toMenuButton);
        stage.addActor(buttonInterface.optionsButton);
        stage.addActor(buttonInterface.quitButton);
        stage.addActor(buttonInterface.optionsTable);
        inputSourisCordonnees = new InputSourisCordonnees(MapScreen.instance.getCamera());
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(input);
        multiplexer.addProcessor(inputSourisCordonnees);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void placeBatiemnt() {
        stage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 screen = inputSourisCordonnees.processMouseCoordinates((int) x, (int) y);
                if (!isInsideTableHitbox(x, y) && !isObstacle((int) screen.x, (int) screen.y)) {
                    if (isClickedBase == true && isBuild == false && clickedThis == "base") {
                        base = new Base("Ma base", 100);
                        base.setX((int) screen.x - 2);
                        base.setY((int) screen.y - 2);
                        isBuild = true;
                        base.ajouterBatiment(base);
                        return true;
                    } else if (isClickedTourelle && isUniqueTourelle == true && clickedThis == "tourelle") {
                        Tourelle tourelle = new Tourelle("Tourelle", 100, 800, 10);
                        if (button == Input.Buttons.LEFT) {
                            tourelle.setX((int) screen.x - 1);
                            tourelle.setY((int) screen.y - 1);
                            base.ajouterBatiment(tourelle);
                            isCreatedTourelle = true;
                            isUniqueTourelle = false;
                        } else if (button == Input.Buttons.RIGHT) {
                            if (tourelle != null) {
                                base.supprimerBatiment(tourelle);
                            }
                        }
                        return true;
                    } else if (isClickedRessource && isUniqueForeuse == true && clickedThis == "foreuse") {
                        Foreuse foreuse = new Foreuse("Foreuse", 100);
                        if (button == Input.Buttons.LEFT) {
                            foreuse.setX((int) screen.x - 1);
                            foreuse.setY((int) screen.y - 1);
                            base.ajouterBatiment(foreuse);
                            isCreatedForeuse = true;
                            isUniqueForeuse = false;
                        } else if (button == Input.Buttons.RIGHT) {
                            if (foreuse != null) {
                                base.supprimerBatiment(foreuse);
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    private void buttonEvent() {
        buttonInterface.pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause();
            }
        });

        buttonInterface.resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resume();
            }
        });

        buttonInterface.optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                options();
            }
        });

        buttonInterface.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause();
            }
        });

        buttonInterface.toMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toMenu();
            }
        });

        buttonInterface.quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonInterface.muteCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isMuted = buttonInterface.muteCheckBox.isChecked();
                if (isMuted) {
                    gameMusic.setVolume(0.f);
                } else {
                    gameMusic.setVolume(volumeMusic);
                }
            }
        });

        buttonInterface.screenSelectBox.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Gérez ici l'événement du SelectBox "Taille de l'écran"
                String selectedSize = buttonInterface.screenSelectBox.getSelected();
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

        buttonInterface.volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                volumeMusic = buttonInterface.volumeSlider.getValue();
                gameMusic.setVolume(volumeMusic);
            }
        });
    }

    private boolean isObstacle(int x, int y) {
        if (mapLayer.getCell(x, y).getTile().getId() == waterTile) {
            return true;
        }
        return false;
    }

    private void batimentButtonCreate() {
        noyauTexture = new Texture(Gdx.files.internal("noyau.png"));
        noyauStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));
        noyauStyle.imageUp = new TextureRegionDrawable(new TextureRegion(noyauTexture));
        noyauButton = new ImageButton(noyauStyle);

        tourelleTexture = new Texture(Gdx.files.internal("tourelle.png"));
        tourelleStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));
        tourelleStyle.imageUp = new TextureRegionDrawable(new TextureRegion(tourelleTexture));
        tourelleButton = new ImageButton(tourelleStyle);

        ressourcesTexture = new Texture(Gdx.files.internal("foreuse.png"));
        ressourcesStyle = new ImageButtonStyle(skin.get(ButtonStyle.class));
        ressourcesStyle.imageUp = new TextureRegionDrawable(new TextureRegion(ressourcesTexture));
        ressourcesButton = new ImageButton(ressourcesStyle);

        noyauButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isClickedBase = true;
                numero++;
                clickedThis = "base";
            }
        });

        tourelleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isClickedTourelle = true;
                numeroUsed.add(numero);
                isUniqueTourelle = true;
                numero++;
                clickedThis = "tourelle";
            }
        });

        ressourcesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isClickedRessource = true;
                numeroUsed.add(numero);
                isUniqueForeuse = true;
                numero++;
                clickedThis = "foreuse";
            }
        });
    }

    private boolean isInsideTableHitbox(float x, float y) {
        float tableX = table.getX();
        float tableY = table.getY();
        float tableWidth = table.getWidth();
        float tableHeight = table.getHeight();
        return x >= tableX && x <= tableX + tableWidth && y >= tableY && y <= tableY + tableHeight;
    }

    private void tableCreate() {
        Drawable tableBg = createDrawable(Color.DARK_GRAY, 0.7f);
        table.setBackground(tableBg);
        table.setWidth(400);
        table.setHeight(300);
        table.setPosition(Gdx.graphics.getWidth() - table.getWidth() - 10, 10);

        table.add(inventory.inventoryTable).center().padBottom(15).row();
        table.add(noyauButton).width(50).height(50);
        table.add(tourelleButton).width(50).height(50);
        table.add(ressourcesButton).width(50).height(50);

        table.setVisible(true);

        stage.addActor(table);

    }

    private Drawable createDrawable(Color color, float alpha) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color.r, color.g, color.b, alpha);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(texture);
    }

    private void createMusic() {
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("theme_game.mp3"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);
        gameMusic.play();
    }

    private void createGameOver() {
        tableOver = new Table();
        Label label = new Label("Game Over", skin);

        tableOver.setBackground(createDrawable(Color.DARK_GRAY, 0.7f));
        tableOver.setVisible(false);
        tableOver.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
        tableOver.setWidth(200);
        tableOver.setHeight(200);
        tableOver.add(label);
        stage.addActor(tableOver);
    }

    private void createWin() {
        tableWin = new Table();
        Label labelFelicitations = new Label("Bravo", skin);
        Label labelConseil = new Label("Essayer plus dur maintenant", skin);

        tableWin.setBackground(createDrawable(Color.DARK_GRAY, 0.7f));
        tableWin.setVisible(false);
        tableWin.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight());
        tableWin.setWidth(200);
        tableWin.setHeight(200);
        tableWin.add(labelFelicitations).row();
        tableWin.add(labelConseil);
        stage.addActor(tableWin);
    }

    public void resize(int width, int height) {
        Vector2 pausePosButton = new Vector2(Gdx.graphics.getWidth() - buttonWidth - padding, Gdx.graphics.getHeight() - buttonHeight - padding);
        Vector2 pausePosition = new Vector2(Gdx.graphics.getWidth() / 2 - 200 / 2, Gdx.graphics.getHeight() / 2 + 200);

        buttonInterface.pauseButton.setPosition(pausePosButton.x, pausePosButton.y);
        buttonInterface.resumeButton.setPosition(pausePosition.x, pausePosition.y);
        buttonInterface.optionsButton.setPosition(pausePosition.x, pausePosition.y - 100);
        buttonInterface.reloadButton.setPosition(pausePosition.x, pausePosition.y - 200);
        buttonInterface.toMenuButton.setPosition(pausePosition.x, pausePosition.y - 300);
        buttonInterface.quitButton.setPosition(pausePosition.x, pausePosition.y - 400);
        buttonInterface.optionsTable.setPosition(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 150);
        table.setPosition(Gdx.graphics.getWidth() - 400 - 10, 10);
        tableOver.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
        tableWin.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
    }

    public void resume() {
        buttonInterface.resumeButton.setVisible(false);
        buttonInterface.quitButton.setVisible(false);
        buttonInterface.optionsButton.setVisible(false);
        buttonInterface.reloadButton.setVisible(false);
        buttonInterface.toMenuButton.setVisible(false);
        buttonInterface.pauseButton.setVisible(true);
        table.setVisible(true);
    }

    public void pause() {
        table.setVisible(false);
        buttonInterface.backButton.setVisible(false);
        buttonInterface.optionsTable.setVisible(false);
        buttonInterface.pauseButton.setVisible(false);
        buttonInterface.resumeButton.setVisible(true);
        buttonInterface.quitButton.setVisible(true);
        buttonInterface.optionsButton.setVisible(true);
        buttonInterface.reloadButton.setVisible(true);
        buttonInterface.toMenuButton.setVisible(true);

    }

    public void options() {
        table.setVisible(false);
        buttonInterface.pauseButton.setVisible(false);
        buttonInterface.resumeButton.setVisible(false);
        buttonInterface.quitButton.setVisible(false);
        buttonInterface.optionsButton.setVisible(false);
        buttonInterface.reloadButton.setVisible(false);
        buttonInterface.toMenuButton.setVisible(false);
        buttonInterface.backButton.setVisible(true);
        buttonInterface.optionsTable.setVisible(true);
    }

    public void gameOver() {
        table.setVisible(false);
        buttonInterface.backButton.setVisible(false);
        buttonInterface.optionsTable.setVisible(false);
        buttonInterface.pauseButton.setVisible(false);
        buttonInterface.pauseButton.setVisible(false);
        buttonInterface.resumeButton.setVisible(false);
        buttonInterface.quitButton.setVisible(false);
        buttonInterface.optionsButton.setVisible(false);
        buttonInterface.reloadButton.setVisible(false);
        buttonInterface.toMenuButton.setVisible(true);
        tableOver.setVisible(true);
    }

    public void gameWin() {
        table.setVisible(false);
        buttonInterface.backButton.setVisible(false);
        buttonInterface.optionsTable.setVisible(false);
        buttonInterface.pauseButton.setVisible(false);
        buttonInterface.pauseButton.setVisible(false);
        buttonInterface.resumeButton.setVisible(false);
        buttonInterface.quitButton.setVisible(false);
        buttonInterface.optionsButton.setVisible(false);
        buttonInterface.reloadButton.setVisible(false);
        buttonInterface.toMenuButton.setVisible(true);
        tableOver.setVisible(false);
        tableWin.setVisible(true);
    }

    public void toMenu() {
        game.goToMenuScreen();
        if (gameMusic != null)
            gameMusic.dispose();
        dispose();
    }

    public void dispose() {
        spriteBatch.dispose();
        noyauTexture.dispose();
        tourelleTexture.dispose();
        ressourcesTexture.dispose();
    }
}
