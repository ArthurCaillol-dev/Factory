package com.factory.pathFinder;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import com.badlogic.gdx.math.Vector2;
import com.factory.enemy.Enemy;

public class PathFinder {
    private TiledMap tiledGameMap = new TmxMapLoader().load("map/mapSprite.tmx");
    private TiledMapTileLayer mapLayer = (TiledMapTileLayer) tiledGameMap.getLayers().get("Calque de Tuiles 1");
    // private TiledMapTileLayer buildingLayer = (TiledMapTileLayer) tiledGameMap.getLayers().get("Obstacle");

    private int waterTile = 197;
    // private int buildingTile = 1;

    private Vector2 start = new Vector2();
    private Vector2 goal = new Vector2();

    private List<Node> path = new ArrayList<Node>();
    private List<Node> nodeToVisit = new ArrayList<Node>();
    private List<Vector2> visitedNodes = new ArrayList<Vector2>();
    

    public PathFinder(Vector2 start, Vector2 goal) {
        this.start = start;
        this.visitedNodes.add(start);
        this.goal = goal;
    }

    public PathFinder(int startX, int startY, int goalX, int goalY) {
        this.start.x = startX;
        this.start.y = startY;
        this.visitedNodes.add(start);
        this.goal.x = goalX;
        this.goal.y = goalY;
    }

    public int getGoalX() {
        return (int) this.goal.x;
    }

    public int getGoalY() {
        return (int) this.goal.y;
    }

    public Vector2 getGoal() {
        return this.goal;
    }

    public List<Node> findPath() {

        if (!Enemy.getEnemyList().isEmpty()) {
            for (Enemy enemy : Enemy.getEnemyList()) {
                enemy.ignoreMove = true;
            }
        }

        if (start.equals(goal)) {
            List<Node> singleNode = new ArrayList<Node>();
            singleNode.add(new Node((int) this.start.x, (int) this.start.y, (int) this.goal.x, (int) this.goal.y, 0));
            return singleNode;
        }
        Node startNode = new Node((int) this.start.x, (int) this.start.y, (int) this.goal.x, (int) this.goal.y, 0);
    
        this.nodeToVisit.addAll(this.getNeighbores(startNode));
        if (this.nodeToVisit.isEmpty()) {
            return null;
        }
        
        int remainingTryCount = 10000000;
        while (remainingTryCount > 0) {
            for (Node node : this.nodeToVisit) {
                if (node.getPosition().x == this.getGoalX() && node.getPosition().y == this.getGoalY()) {
                    this.reconstructPath(node);
                    return this.path;
                }
            }

            Node nextNode = this.nodeToVisit.get(0);
            
            this.nodeToVisit.remove(nextNode);
            this.visitedNodes.add(nextNode.getPosition());
            this.nodeToVisit.addAll(this.getNeighbores(nextNode));
            
            remainingTryCount--;
        }

        return null;
    }

    private void reconstructPath(Node endNode) {
        this.path.clear();

        this.path.add(endNode);
        Node previousNode = endNode.getParent();
        this.path.add(previousNode);

        while (previousNode.getPosition().x != this.start.x && previousNode.getPosition().y != this.start.y) {
            previousNode = previousNode.getParent();
            this.path.add(previousNode);
        }
    }

    private List<Node> getNeighbores(Node node) {
        List<Node> neighbores = new ArrayList<Node>();

        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, 
                              {0,  -1},          {0,  1}, 
                              {1,  -1}, {1,  0}, {1,  1}};

        for (int[] direction : directions) {
            int newX = (int) node.getPosition().x + direction[0];
            int newY = (int) node.getPosition().y + direction[1];

            if (isTileOutsideMap(newX, newY)) {
                continue;
            }
            
            if (isObstacle(newX, newY)) {
                continue;
            }

            if (isNodeAlreadyVisited(newX, newY)) {
                continue;
            }

            // int[] newCoordinates = {newX, newY};
            this.visitedNodes.add(new Vector2(newX, newY));
            neighbores.add(new Node(newX, newY, this.getGoalX(), this.getGoalY(), node.getGScore() + 1, node));
        }

        return neighbores;
    }

    private boolean isTileOutsideMap(int x, int y) {
        
        if (x < 0 || x >= mapLayer.getWidth() || y < 0 || y >= mapLayer.getHeight()) {
            return true;
        }

        return false;
    }

    private boolean isNodeAlreadyVisited(int x, int y) {

        for (Vector2 coordinates : visitedNodes) {
            if ((coordinates.x == x) && (coordinates.y == y)) {
                return true;
            }
        }

        // if (this.visitedNodes.contains(coordinates)) {
        // }

        return false;
    }

    private boolean isObstacle(int x, int y) {
        
        if (mapLayer.getCell(x, y).getTile().getId() == waterTile) {
            return true;
        }

        // if (buildingLayer.getCell(x, y).getTile().getId() == buildingTile) {
        //     return true;
        // }

        return false;
    }

}
