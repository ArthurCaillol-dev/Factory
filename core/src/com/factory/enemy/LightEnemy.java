package com.factory.enemy;

public abstract class LightEnemy extends Enemy {

    public LightEnemy(String name) {
        this.name = name;
        this.hp = 100;
        this.damage = 10;
    }

    public LightEnemy(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

}
