// BatimentRenderer.java
package com.factory.batiment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BatimentRenderer implements BatimentVisitor {
    private SpriteBatch batch;
    private  Rectangle hitbox;
    private Texture baseTexture;
    private Texture tourelleTexture;
    private Texture foreuseTexture;

    public BatimentRenderer(SpriteBatch batch) {
        this.batch = batch;
        this.hitbox = new Rectangle();

        baseTexture = new Texture("noyau.png");
        tourelleTexture = new Texture("tourelle.png");
        foreuseTexture = new Texture("foreuse.png");
    }

    @Override
    public void visit(Base base) {
        batch.draw(baseTexture, base.getX(), base.getY(),4,4);
        // Mettez à jour la hitbox pour la base
        hitbox.set(base.getX(), base.getY(), 4, 4);
    }

    @Override
    public void visit(Tourelle tourelle) {
        batch.draw(tourelleTexture, tourelle.getX(), tourelle.getY(),2,2);
        hitbox.set(tourelle.getX(), tourelle.getY(), 2, 2);
    }

    @Override
    public void visit(Foreuse foreuse) {
        batch.draw(foreuseTexture, foreuse.getX(), foreuse.getY(),2,2);
        hitbox.set(foreuse.getX(), foreuse.getY(), 2, 2);
    }

    // Ajoutez une méthode pour obtenir la hitbox
    public Rectangle getHitbox() {
        return hitbox;
    }

    public Texture getBaseTexture() {
        return baseTexture;
    }

    public Texture getTourelleTexture() {
        return tourelleTexture;
    }
}
