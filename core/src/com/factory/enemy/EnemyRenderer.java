package com.factory.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyRenderer implements EnemyVisitor {
    private SpriteBatch batch;

    // Store textures to dispose of them later
    private Texture cosmicCrawlerTexture;
    private Texture exoplanetEnforcerTexture;
    private Texture voidstarOverlordTexture;

    public EnemyRenderer(SpriteBatch batch) {
        this.batch = batch;
        // Load textures in the constructor
        cosmicCrawlerTexture = new Texture("CosmicCrawler.png");
        exoplanetEnforcerTexture = new Texture("ExoplanetEnforcer.png");
        voidstarOverlordTexture = new Texture("VoidstarOverlord.png");
    }

    @Override
    public void visit(CosmicCrawler cosmicCrawler) {
        Texture texture = new Texture("CosmicCrawler.png");
        batch.draw(texture, cosmicCrawler.getX(), cosmicCrawler.getY(), cosmicCrawler.getSize(), cosmicCrawler.getSize());
    }

    @Override
    public void visit(ExoplanetEnforcer exoplanetEnforcer) {
        Texture texture = new Texture("ExoplanetEnforcer.png");
        batch.draw(texture, exoplanetEnforcer.getX(), exoplanetEnforcer.getY(), exoplanetEnforcer.getSize(), exoplanetEnforcer.getSize());
    }

    @Override
    public void visit(VoidstarOverlord voidstarOverlord) {
        Texture texture = new Texture("VoidstarOverlord.png");
        batch.draw(texture, voidstarOverlord.getX(), voidstarOverlord.getY(), voidstarOverlord.getSize(), voidstarOverlord.getSize());
    }

    // Dispose of textures in the dispose method
    public void dispose() {
        cosmicCrawlerTexture.dispose();
        exoplanetEnforcerTexture.dispose();
        voidstarOverlordTexture.dispose();
    }
}
