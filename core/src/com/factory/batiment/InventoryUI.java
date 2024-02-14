package com.factory.batiment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class InventoryUI {
    public Table inventoryTable;
    private Label copperLabel, ironLabel, coalLabel, titaniumLabel;
    private static Skin skin = new Skin(Gdx.files.internal("skin-button/skins/visui/assets/uiskin.json"));

    private int copperCount, ironCount, coalCount, titaniumCount;

    public InventoryUI() {
        inventoryTable = new Table();
        inventoryTable.top().center();
        // inventoryTable.setFillParent(true);

        copperLabel = new Label("0 cuivre", skin);
        ironLabel = new Label("0 fer", skin);
        coalLabel = new Label("0 charbon", skin);
        titaniumLabel = new Label("0 titane", skin);

        inventoryTable.add(new Label("Stock actuel:", skin)).padBottom(10).row();
        inventoryTable.add(copperLabel).padBottom(5).row();
        inventoryTable.add(ironLabel).padBottom(5).row();
        inventoryTable.add(coalLabel).padBottom(5).row();
        inventoryTable.add(titaniumLabel).padBottom(5).row();
    }

    public void updateInventory(int copper, int iron, int coal, int titanium) {
        copperCount = copper;
        ironCount = iron;
        coalCount = coal;
        titaniumCount = titanium;

        copperLabel.setText(copperCount + " cuivre");
        ironLabel.setText(ironCount + " fer");
        coalLabel.setText(coalCount + " charbon");
        titaniumLabel.setText(titaniumCount + " titane");
    }
}