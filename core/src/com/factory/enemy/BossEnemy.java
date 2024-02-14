package com.factory.enemy;

public abstract class BossEnemy extends Enemy {

    public BossEnemy(String name) {
        this.name = name;
        this.hp = 2000;
        this.damage = 250;
    }

    public BossEnemy(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }
}
