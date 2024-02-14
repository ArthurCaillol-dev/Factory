package com.factory.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.factory.batiment.*;
import com.factory.pathFinder.*;

public abstract class Enemy implements Unit {
    protected String name;
    protected int hp;
    protected int damage;
    protected Vector2 position = new Vector2();
    protected float max_velocity = 1;
    protected float size = 1;
    public boolean ignoreMove = true;

    protected float lastAttackTime = 1.0f;
    protected float attackCooldown = 1.0f;

    protected List<Node> path = new ArrayList<Node>();

    private static List<Enemy> enemyList = new ArrayList<Enemy>();


    public static List<Enemy> getEnemyList() {
        return enemyList;
    }

    public static void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    public static void addEnemy(List<Enemy> enemies) {
        enemyList.addAll(enemies);
    }

    public static boolean removeEnemy(Enemy enemy) {
        if (enemyList.contains(enemy)) {
            enemyList.remove(enemy);
            return true;
        }

        return false;
    }

    public static void removeEnemy(List<Enemy> enemies) {
        enemyList.removeAll(enemies);
    }



    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean Attack(ArrayList<Batiment> buildings, float delta) {

        // if (isAnEnemyGeneratingPath()) {
        //     return false;
        // }

        if (this.ignoreMove) {
            delta = 0;
        }

        if (this.AttackTurret(buildings, delta)) {
            return true;
        }

        Batiment base = buildings.get(0);
        Vector2 distance = new Vector2(this.getX() - base.getX(), this.getY() - base.getY());

        if (distance.len() > 3) {
            this.followPath(delta, base);
            return true;
        }

        this.lastAttackTime += delta;

        if (this.lastAttackTime > this.attackCooldown) {
            this.lastAttackTime = 0;
            base.receiveDamage(this.getDamage());
            System.out.println("Enemy : " + this.getName() + " attacked " + base.getNom());
            System.out.println(this.getName() + " hp : " + this.getHp() + " | " + base.getNom() + " hp : " + base.getHp());
        }

        return true;
    }

    private boolean AttackTurret(ArrayList<Batiment> buildings, float delta) {
        for (Batiment building : buildings) {
            if (!(building instanceof Tourelle)) {
                continue;
            }

            Vector2 distance = new Vector2(building.getX() - this.getX(), building.getY() - this.getY());

            if (distance.len() > 3) {
                continue;
            }

            this.lastAttackTime += delta;
            if (this.lastAttackTime > this.attackCooldown) {
                this.lastAttackTime = 0;
                building.receiveDamage(this.getDamage());
                System.out.println("Enemy : " + this.getName() + " attacked " + building.getNom());
                System.out.println(this.getName() + " hp : " + this.getHp() + " | " + building.getNom() + " hp : " + building.getHp());
            }
            return true;
        }

        return false;
    }

    @Override
    public void receiveDamage(int damage) {
        if (this.hp <= 0) {
            Enemy.removeEnemy(this);
            return;
        }

        if (damage <= 0) {
            damage = 1;
        }

        this.hp -= damage;

        if (this.hp < 0) {
            this.hp = 0;
            // Enemy.removeEnemy(this);
        }

    }

    @Override
    public float getSize() {
        return this.size;
    }

    @Override
    public float getX() {
        return this.position.x;
    }

    @Override
    public float getY() {
        return this.position.y;
    }

    @Override
    public Vector2 getPositon() {
        return this.position;
    }

    @Override
    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public void setX(float x_position) {
        this.position.x = x_position;
    }

    @Override
    public void setY(float y_position) {
        this.position.y = y_position;
    }

    @Override
    public void setPosition(float x_position, float y_position) {
        this.position.x = x_position;
        this.position.y = y_position;
    }

    @Override
    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
    }

    @Override
    public void moveTo(float delta, Vector2 destination) {
        Vector2 direction = new Vector2(destination.x - this.getX(), destination.y - this.getY());
        float length = direction.len();

        if (length < 0.1) {
            this.setX((int) destination.x);
            this.setY((int) destination.y);
            return;
        }

        direction.x /= length;
        direction.y /= length;
        this.setX(this.getX() + (this.max_velocity * direction.x * delta));
        this.setY(this.getY() + (this.max_velocity * direction.y * delta));
    }

    private boolean isPathValid(float x, float y) {
        return this.isPathValid((int) x, (int) y);
    }

    private boolean isPathValid(int x, int y) {

        if (this.path == null) {
            return false;
        }

        if (this.path.isEmpty()) {
            return false;
        }

        if (this.path.get(0).getPosition().x != x || this.path.get(0).getPosition().y != y) {
            return false;
        }

        return true;
    }
    
    private void followPath(float delta, Batiment building) {

        if (!this.isPathValid(building.getX(), building.getY())) {
            this.ignoreMove = true;

            PathFinder pathFinder = new PathFinder((int) this.getX(), (int) this.getY(), (int) building.getX(), (int) building.getY());
            this.path = pathFinder.findPath();
            pathFinder = null;
        }

        if (this.path == null || this.path.isEmpty()) {
            return;
        }

        Node nextNode = this.path.get(this.path.size() - 1);
        Vector2 nextNodePosition = new Vector2(nextNode.getPosition().x, nextNode.getPosition().y);

        if (this.ignoreMove) {
            this.ignoreMove = false;
            return;
        }

        moveTo(delta, nextNodePosition);

        Vector2 distanceToNextNode = new Vector2(this.getPositon().x - nextNodePosition.x, this.getPositon().y - nextNodePosition.y);

        if (distanceToNextNode.len() < 0.2) {
            this.path.remove(nextNode);
        }
    }
}
