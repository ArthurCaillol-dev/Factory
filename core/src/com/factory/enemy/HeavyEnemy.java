package com.factory.enemy;

public abstract class HeavyEnemy extends Enemy {

    public HeavyEnemy(String name) {
        this.name = name;
        this.hp = 500;
        this.damage = 50;
    }

    public HeavyEnemy(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }
    
}
