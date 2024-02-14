package com.factory.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.factory.Input.GestInput;
import com.factory.Input.InputSourisCordonnees;
import com.factory.batiment.*;
import com.factory.enemy.*;
import com.factory.game.GameLoop;


public class GameScreen implements Screen {
    private GameLoop game;
    private SpriteBatch spriteBatch;
    private MapScreen map;
    private GestInput input;
    private UserInterface userInterface;
    BatimentRenderer renderer;
    EnemyRenderer enemyRenderer;
    public static Stage stage;
    float test = (float) 0.0;

    private float phasePrepa = 20f;
    private float prepaBtwWaves = 10f;
    private float phaseWaves = 10f;
    private float elapsedTime = 0f;
    private int waveNumber = 1;
    private boolean isPreparing = true;
    private boolean isWave = false;
    private boolean isprepaBtwWaves = false;
    private boolean switchExecuted = false;

    InputSourisCordonnees inputSourisCordonnees;

    private float tempsDerniereCollecte = 0f;
    private float intervalleCollecte = 1f;
    private Inventaire inventaire;

    Difficulty difficulty;

    private ArrayList<Bullet> bullets;

    public GameScreen(GameLoop gameLoop, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.game = gameLoop;
        userInterface = new UserInterface();
        map = new MapScreen();
        bullets = new ArrayList<Bullet>();
    }

    @Override
    public void show() {
        userInterface.create();
        spriteBatch = userInterface.spriteBatch;
        renderer = new BatimentRenderer(spriteBatch);
        enemyRenderer = new EnemyRenderer(spriteBatch);
        inventaire = new Inventaire();

        input = userInterface.input;
        stage = userInterface.stage;
        inputSourisCordonnees = userInterface.inputSourisCordonnees;
        userInterface.game = game;
    }

    private void printBase() {
        if (userInterface.base != null) {
            ArrayList<Batiment> destroyedBuildings = new ArrayList<Batiment>();
            
            for (Batiment building : userInterface.base.getBatiments()) {
                if (!(building instanceof Tourelle)) {
                    if (building.getHp() <= 0) {
                        gameOver();
                    }
                }

                if (building.getHp() <= 0) {
                    destroyedBuildings.add(building);
                }
            }

            userInterface.base.removeBatiment(destroyedBuildings);
            destroyedBuildings.clear();

            userInterface.base.removeBatiment(destroyedBuildings);
            destroyedBuildings.clear();

            ArrayList<Batiment> batiments = userInterface.base.getBatiments();

            for (Batiment b : batiments) {
                Vector2 batPosition = new Vector2(b.getX(), b.getY());
                b.setX(batPosition.x);
                b.setY(batPosition.y);
                if (batPosition.x < 0)
                    b.setX(0);
                else if (batPosition.x > 176)
                    b.setX(176);
                if (batPosition.y < 0)
                    b.setY(0);
                else if (batPosition.y > 116)
                    b.setY(116);
                b.accept(renderer);
            }
        }
    }

    private void updateEnemy(float delta) {
        if (Enemy.getEnemyList().isEmpty()) {
            return;
        }

        ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
        for (Enemy enemy : Enemy.getEnemyList()) {
            if (enemy.getHp() <= 0) {
                deadEnemies.add(enemy);
            }
        }

        Enemy.removeEnemy(deadEnemies);
        deadEnemies.clear();

        if (userInterface.base != null) {
            updateEnemyTarget(delta, userInterface.base.getBatiments());

            // Vérifiez si la tourelle associée à une balle a été détruite
            ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
            for (Bullet bullet : bullets) {
                if (bullet.getTourelle() != null && !userInterface.base.getBatiments().contains(bullet.getTourelle())) {
                    // La tourelle associée à cette balle a été détruite, marquez la balle pour suppression
                    bulletsToRemove.add(bullet);
                }
            }
            bullets.removeAll(bulletsToRemove);
        }

        printEnemy(delta);
    }

    private void updateEnemyTarget(float delta, ArrayList<Batiment> buildings) {
        if (buildings == null || buildings.isEmpty()) {
            return;
        }

        for (Enemy enemy : Enemy.getEnemyList()) {
            enemy.Attack(buildings, delta);
        }
    }

    private void printEnemy(float delta) {
        for (Enemy enemy : Enemy.getEnemyList()) {
            if (enemy instanceof CosmicCrawler) {
                enemyRenderer.visit((CosmicCrawler) enemy);
            }

            if (enemy instanceof ExoplanetEnforcer) {
                enemyRenderer.visit((ExoplanetEnforcer) enemy);
            }

            if (enemy instanceof VoidstarOverlord) {
                enemyRenderer.visit((VoidstarOverlord) enemy);
            }
        }
    }


    private void spawnEnemy(int nbWave) {
        if (isWave && !switchExecuted) {

            if (nbWave % 5 == 0) {
                for (int i = 0; i < nbWave / 3; i++) {
                    Enemy.addEnemy(new VoidstarOverlord(6, i * 4));
                }
            }

            if (nbWave % 2 == 0) {
                for (int i = 0; i < nbWave / 2; i++) {
                    Enemy.addEnemy(new ExoplanetEnforcer(3, i * 3));
                }
            }

            for (int i = 0; i < (int) nbWave * 2.5f; i++) {
                Enemy.addEnemy(new CosmicCrawler(1, i * 2));
                if (i >= 10) {
                    break;
                }
            }

            switchExecuted = true;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        map.renderer.setView(map.getCamera());
        map.camera.update();
        map.renderer.render();

        timer(delta);
        spawnEnemy(waveNumber);
        printBase();
        updateEnemy(delta);

        tempsDerniereCollecte += delta;
        if (userInterface.base != null && !userInterface.base.getBatiments().isEmpty()){
            if (tempsDerniereCollecte >= intervalleCollecte) {
                collecteDesRessources();
                tempsDerniereCollecte = 0f;
            }
        }
        if (userInterface.base != null && !userInterface.base.getBatiments().isEmpty()){
            tirTourelle();
            ArrayList<Bullet> bulletRemove = new ArrayList<Bullet>();
            for (Bullet bullet : bullets) {
                bullet.update(delta);
                if (bullet.remove) {
                    bulletRemove.add(bullet);
                } else {
                    bullet.render(spriteBatch);
                }
            }
        }

        input.updateCameraMovement();
        spriteBatch.end();

        input.updateCameraMovement();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();

        stage.getViewport().getCamera().update();
        stage.getViewport().apply();
    }

    private void timer (float delta) {
        elapsedTime += delta;

        if (isPreparing) {
            if (elapsedTime >= phasePrepa) {
                System.out.println("Phase de préparation initiale terminée. Début de la phase de vagues.");
                elapsedTime = 0f;
                isPreparing = false;
                isWave = true;
                switchExecuted = false;
            }
        } else if (isWave) {
            if (elapsedTime >= phaseWaves) {
                System.out.println("Vagues " + waveNumber +": terminée. Début de la phase de préparation entre les vagues.");
                elapsedTime = 0f;
                isWave = false;
                isprepaBtwWaves = true;
                waveNumber++;
                phaseWaves += 10;
            }
        } else if (isprepaBtwWaves) {
            if (elapsedTime >= prepaBtwWaves) {
                System.out.println("Phase de préparation entre les vagues terminée. Début d'une nouvelle phase de vagues.");
                isprepaBtwWaves = false;
                isWave = true;
                elapsedTime = 0f;
                switchExecuted = false;
            }
        } 
        // else if (waveNumber == 6) {
        //     gameWin();
        // }
    }

    private void collecteDesRessources() {
        ArrayList<Batiment> batiments = userInterface.base.getBatiments();
        for (Batiment b : batiments) {
            if (b instanceof Foreuse) {
                Foreuse f = (Foreuse) b;
                f.collecterRessource((int) b.getX(), (int) b.getY(),inventaire);
                printRessources();
            }
        }
    }

    private void printRessources() {
        int copper = inventaire.getQuantiteRessource("Cuivre");
        int iron = inventaire.getQuantiteRessource("Fer");
        int coal = inventaire.getQuantiteRessource("Charbon");
        int titanium = inventaire.getQuantiteRessource("Titanium");

        userInterface.inventory.updateInventory(copper, iron, coal, titanium);
        // System.out.println("Stock actuel : " + inventaire.getQuantiteRessource("Cuivre") + " Cuivre, "
        // + inventaire.getQuantiteRessource("Fer") + " Fer, " + inventaire.getQuantiteRessource("Charbon")
        //         + " Charbon, " + inventaire.getQuantiteRessource("Titanium") + " Titanium");
    }

    private void tirTourelle() {
        ArrayList<Batiment> batiments = userInterface.base.getBatiments();
        for (Batiment b : batiments) {
            if (b instanceof Tourelle) {
                Tourelle tourelle = (Tourelle) b;
                for (Enemy enemy : Enemy.getEnemyList()) {
                    if (tourelle.isInRange(enemy)){
                        Bullet bullet = new Bullet(tourelle.getX(), tourelle.getY(), enemy, tourelle, 10);
                        bullets.add(bullet);
                        tourelle.attaquerEnnemi(enemy);
                        break;
                    }
                }
            }
        }
    }

    public void gameOver() {
        userInterface.gameOver();
    }

    public void gameWin() {
        userInterface.gameWin();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        userInterface.resize(width, height);
    }

    @Override
    public void pause() {
        userInterface.resume();
    }

    @Override
    public void resume() {
        userInterface.resume();
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        userInterface.dispose();
        enemyRenderer.dispose();
    }
}
