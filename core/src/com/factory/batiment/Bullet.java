package com.factory.batiment;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.factory.enemy.Enemy;

public class Bullet {
    float x,y;
    public int speed = 500;
    private Texture bulletTexture;
    public boolean remove = false;
    private Enemy enemy;
    private Tourelle tourelle;


    public Bullet (float x, float y, Enemy enemy,Tourelle tourelle, int bulletSpeed) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.speed = bulletSpeed;
        this.tourelle = tourelle;
        bulletTexture = new Texture("tir.png");
    }
    public Tourelle getTourelle() {
        return tourelle;
    }

    public void update(float deltaTime) {
        if (enemy != null && enemy.getHp() > 0) {
            // Calculate the direction vector
            float directionX = enemy.getX() - x;
            float directionY = enemy.getY() - y;
            float distance = (float) Math.sqrt(directionX * directionX + directionY * directionY);

            // Normalize the direction vector
            directionX /= distance;
            directionY /= distance;

            // Update the bullet position based on the direction and speed
            x += directionX * speed * deltaTime;
            y += directionY * speed * deltaTime;
        } else {
            // The target is dead or null, mark the bullet for removal
            remove = true;
        }

        // Check if the bullet is out of bounds
        if (y > Gdx.graphics.getHeight()) {
            remove = true;
        }
    }

    public void render (SpriteBatch batch) {
        batch.draw(bulletTexture, x, y,1,1);
    }
}
