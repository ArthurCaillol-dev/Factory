package com.factory.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonUI {

    private static Skin skin = new Skin(Gdx.files.internal("skin-button/skins/visui/assets/uiskin.json"));
    public ArrayList<TextButton> buttonList = new ArrayList<>();
    public TextButton pauseButton;
    public TextButton optionsButton;
    public TextButton reloadButton;
    public TextButton toMenuButton;
    public TextButton resumeButton;
    public TextButton quitButton;

    public TextButton backButton;
    public Table optionsTable;
    public Label screenLabel;
    public SelectBox<String> screenSelectBox;
    public Label volumeLabel;
    public Slider volumeSlider;
    public CheckBox muteCheckBox;

    private final float buttonWidth = 200;
    private final float buttonHeight = 75;
    private boolean isVisible = false;
    private Vector2 position = new Vector2(Gdx.graphics.getWidth() / 2 - 200 / 2, (Gdx.graphics.getHeight() / 2) + 200);

    public void buttonCreate() {
        pauseButton = createButton("Pause", skin, position, true, 0, 0);
        resumeButton = createButton("Continuer", skin, position, isVisible, 0, 100);
        optionsButton = createButton("Options", skin, position, isVisible, 0, 200);
        reloadButton = createButton("Recommencer", skin, position, isVisible, 0, 300);
        toMenuButton = createButton("Menu", skin, position, isVisible, 0, 400);
        quitButton = createButton("Quitter", skin, position, isVisible, 0, 500);
        optionsCreate();
    }

    public TextButton createButton(String name, Skin skin, Vector2 position, boolean visible, int modificateurPosX, int modificateurPosY) {
        TextButton newButton = new TextButton(name, skin);

        newButton.setWidth(buttonWidth);
        newButton.setHeight(buttonHeight);
        newButton.setPosition(position.x, position.y);
        newButton.setVisible(visible);
        position.y -= modificateurPosY;
        position.x -= modificateurPosX;
        buttonList.add(newButton);

        return (newButton);
    }

    public void optionsCreate() {
        Drawable tableBg = createDrawable(Color.DARK_GRAY, 0.7f);
        backButton = createButton("<--- Retour", skin, position, isVisible, 0, 0);
        backButton.setPosition(0, 0);
        optionsTable = new Table();
        optionsTable.setVisible(false);
        optionsTable.setWidth(400);
        optionsTable.setHeight(300);
        optionsTable.setPosition(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 150);

        // optionsTable.debug();

        Table tableChildren = new Table();
        tableChildren.setFillParent(false); // Utilisez setFillParent(true) pour que tableChildren prenne toute la place
        tableChildren.setVisible(true);
        tableChildren.setBackground(tableBg); // Définissez le fond de tableChildren

        // Taille de l'écran
        screenLabel = new Label("Taille de l'écran:", skin);
        screenSelectBox = new SelectBox<>(skin);
        screenSelectBox.setItems("Resolution", "Plein écran", "1920x1080", "1280x720", "800x600");

        // Réglage du son
        volumeLabel = new Label("Volume:", skin);
        volumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        muteCheckBox = new CheckBox("Muet", skin);

        optionsTable.add(backButton).padRight(200).padBottom(100).width(buttonWidth).height(buttonHeight).row();
        tableChildren.add(screenLabel).padBottom(10).width(100).height(35).row();
        tableChildren.add(screenSelectBox).padBottom(20).width(100).height(35).row();
        tableChildren.add(volumeLabel).padBottom(10).width(100).height(35).row();
        tableChildren.add(volumeSlider).padBottom(20).width(100).height(35).row();
        tableChildren.add(muteCheckBox).padBottom(20).width(100).height(35).row();

        optionsTable.add(tableChildren).expand().fill(); // Utilisez expand().fill() pour que tableChildren prenne toute la place
    }


    private Drawable createDrawable(Color color, float alpha) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color.r, color.g, color.b, alpha);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(texture);
    }

    public void dispose() {
        for (TextButton b : buttonList) {
            b.clear();
        }
        skin.dispose();
    }
}
