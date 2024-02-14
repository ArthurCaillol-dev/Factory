package com.factory.batiment;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Foreuse extends BatimentRessource {
    private int quantiteRessource;
    private Inventaire inventaire = Inventaire.getInstance();
    private TiledMap tiledGameMap = new TmxMapLoader().load("map/mapSprite.tmx");
    private TiledMapTileLayer mapLayer = (TiledMapTileLayer) tiledGameMap.getLayers().get("Calque de Tuiles 1");
    private TiledMapTileLayer mineraiLayer = (TiledMapTileLayer) tiledGameMap.getLayers().get("Calque de Tuiles 2");

    private int cuivreTile = 236;
    private int ferTile = 196;
    private int charbonTile = 167;
    private int titaniumTile = 146;
    private int herbeTile = 0;
    private static boolean premiereForeuse = true;
    private boolean secondForeuse = true;

    public Foreuse(String nom, int hp) {
        super(nom, hp);
        if (premiereForeuse) {
            // Ne retire pas de cuivre pour la première foreuse
            premiereForeuse = false;
        } else {
            // Logique de déduction de ressources pour la construction
            if (secondForeuse) {
                // ...
            } else {
                if (!deductResourcesForConstruction("Cuivre", 15)) {
                    // Handle the case where construction fails due to insufficient resources
                    throw new RuntimeException("Insufficient resources for Foreuse construction");
                }
            }
        }
        
    }

    private boolean deductResourcesForConstruction(String nomRessource, int quantite) {
        int currentQuantity = inventaire.getQuantiteRessource(nomRessource);

        if (currentQuantity >= quantite) {
            inventaire.retirerRessource(nomRessource, quantite);
            return true; // Construction successful
        } else {
            return false; // Construction failed due to insufficient resources
        }
    }

    public int getQuantiteRessource() {
        return quantiteRessource;
    }

    public void collecterRessource(int x, int y, Inventaire inventaire) {
        if (isCuivre(x, y)) {
            inventaire.ajouterRessource("Cuivre", 1);
            // System.out.println("Ressource collectée : Cuivre");
        } else if (isFer(x, y)) {
            inventaire.ajouterRessource("Fer", 1);
            // System.out.println("Ressource collectée : Fer");
        } else if (isCharbon(x, y)) {
            inventaire.ajouterRessource("Charbon", 1);
            // System.out.println("Ressource collectée : Charbon");
        } else if (isTitanium(x, y)) {
            inventaire.ajouterRessource("Titanium", 1);
            // System.out.println("Ressource collectée : Titanium");
        } else if (isWeed(x, y)) {
            // System.out.println("Ressource collectée : Weed");
        }
    }

    @Override
    public void accept(BatimentVisitor visitor) {
        visitor.visit(this);
    }

    private boolean isCuivre(int x, int y) {
        return mapLayer.getCell(x, y).getTile().getId() == cuivreTile;
    }

    private boolean isFer(int x, int y) {
        return mapLayer.getCell(x, y).getTile().getId() == ferTile;
    }

    private boolean isCharbon(int x, int y) {
        try {
            return mineraiLayer.getCell(x, y).getTile().getId() == charbonTile;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTitanium(int x, int y) {
        try {
            return mineraiLayer.getCell(x, y).getTile().getId() == titaniumTile;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isWeed(int x, int y) {
        try {
            return mineraiLayer.getCell(x, y).getTile().getId() == herbeTile;
        } catch (Exception e) {
            return false;
        }
    }
}
