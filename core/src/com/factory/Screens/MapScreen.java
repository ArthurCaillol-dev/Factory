package com.factory.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapScreen extends Map{
    public static MapScreen instance;
    public OrthogonalTiledMapRenderer renderer;
    private float unitScale;
    private TiledMap map;
    public OrthographicCamera camera;
    private int mapWidth;
    private int mapHeight;
    FitViewport viewport;


    private void setSizeScreen() {
        this.mapWidth = map.getProperties().get("width", Integer.class);
        this.mapHeight = map.getProperties().get("height", Integer.class);
        map.getProperties().get("tilewidth", Integer.class);
        map.getProperties().get("tileheight", Integer.class);
    }

    public MapScreen() {
        this.map = new TmxMapLoader().load("map/mapSprite.tmx");
        this.unitScale = 1 / 32f;
        this.renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        this.camera = new OrthographicCamera();
        setSizeScreen();
        this.camera.setToOrtho(false, 50, 30);
        this.camera.position.set(this.mapWidth * 0.5f, this.mapHeight * 0.5f + 20, 0);
        this.camera.update();
        instance = this;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void translateCamera(int speedX, int speedY) {
        float previousX = camera.position.x;
        float previousY = camera.position.y;

        camera.translate(speedX, speedY);
        camera.update();

        if (camera.position.x < camera.viewportWidth / 2f || camera.position.x > mapWidth - camera.viewportWidth / 2f) {
            camera.position.x = previousX;
        }
        if (camera.position.y < camera.viewportHeight / 2f || camera.position.y > mapHeight - camera.viewportHeight / 2f) {
            camera.position.y = previousY;
        }
        camera.update();
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    @Override
    public void dispose() {
        this.map.dispose();
        this.renderer.dispose();
    }

    public Vector3 getCameraPosition() {
        return new Vector3(camera.position);
    }
}
