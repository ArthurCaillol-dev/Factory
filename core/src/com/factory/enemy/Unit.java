package com.factory.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.factory.batiment.Batiment;

public interface Unit {
    public String getName();
    public int getHp();
    public int getDamage();
    public boolean Attack(ArrayList<Batiment> buildings, float delta);
    public void receiveDamage(int damage);
    public float getSize();
    public float getX();
    public float getY();
    public Vector2 getPositon();
    public void setSize(float size);
    public void setX(float x_position);
    public void setY(float y_position);
    public void setPosition(float x_position, float y_position);
    public void setPosition(Vector2 newPosition);
    public void moveTo(float delta, Vector2 destination);
}