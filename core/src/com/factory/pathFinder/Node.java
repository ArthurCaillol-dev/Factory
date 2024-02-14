package com.factory.pathFinder;

// import com.badlogic.gdx.maps.tiled.TiledMap;
// import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
// import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

public class Node {
    private Vector2 position = new Vector2();
    private int totalCost;
    private int gScore;
    private int hScore;
    private int goalX;
    private int goalY;
    private Node parent;

    private boolean obstacle = false;
    private boolean destructible = false;
    // private int waterTile = 197;
    // private int buildingTile = 1;


    // private TiledMap tiledGameMap = new TmxMapLoader().load("map/mapSprite.tmx");
    // private TiledMapTileLayer mapLayer = (TiledMapTileLayer) tiledGameMap.getLayers().get("Calque de Tuiles 1");
    // private TiledMapTileLayer buildingLayer = (TiledMapTileLayer) tiledGameMap.getLayers().get("Obstacle");


    public Node(int x, int y, int goalX, int goalY, int gScore, Node parent) {
        this.position.x = x;
        this.position.y = y;
        this.gScore = gScore;
        this.parent = parent;
        this.hScore = this.getHeuristicCost();
        this.totalCost = this.gScore + this.hScore;

        // if (mapLayer.getCell(x, y).getTile().getId() == waterTile) {
        //     this.obstacle = true;
        // }

        // if (buildingLayer.getCell(x, y).getTile().getId() == buildingTile) {
        //     this.obstacle = true;
        //     this.destructible = true;
        // }
    }

    public Node(int x, int y, int goalX, int goalY, int gScore) {
        this.position.x = x;
        this.position.y = y;
        this.gScore = gScore;
        this.parent = null;
        this.hScore = this.getHeuristicCost();
        this.totalCost = this.gScore + this.hScore;

        // if (mapLayer.getCell(x, y).getTile().getId() == waterTile) {
        //     this.obstacle = true;
        // }

        // if (buildingLayer.getCell(x, y).getTile().getId() == buildingTile) {
        //     this.obstacle = true;
        //     this.destructible = true;
        // }
    }

    public boolean isObstacle() {
        return this.obstacle;
    }

    public boolean isDestructible() {
        return this.destructible;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public int getGScore() {
        return this.gScore;
    }

    public int getHScore() {
        return this.hScore;
    }

    public Node getParent() {
        return this.parent;
    }

    public float getTotalCost() {
        return this.totalCost;
    }

    private int getHeuristicCost() {
        return (int) (Math.abs(this.position.x - this.goalX) + Math.abs(this.position.y - this.goalY));
    }

    // private boolean isTileOutsideMap(int x, int y) {
        
    //     if (x < 0 || x > mapLayer.getWidth() || y < 0 || y > mapLayer.getHeight()) {
    //         return true;
    //     }

    //     return false;
    // }

    // public List<Node> getNeighbores() {
    //     List<Node> neighbores = new ArrayList<Node>();

    //     int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, 
    //                           {0,  -1},          {0,  1}, 
    //                           {1,  -1}, {1,  0}, {1,  1}};

    //     for (int[] direction : directions) {
    //         int newX = (int) this.position.x + direction[0];
    //         int newY = (int) this.position.y + direction[1];

    //         if (isTileOutsideMap(newX, newY)) {
    //             continue;
    //         }
            
    //         neighbores.add(new Node(newX, newY, this.goalX, this.goalY, this.gScore + 1, this));
    //     }

    //     return neighbores;
    // }
}
