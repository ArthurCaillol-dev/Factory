package com.factory.enemy;

public class CosmicCrawler extends LightEnemy {

    private static int cosmicCrawlerCount = 0;

    public CosmicCrawler() {
        super("Cosmic Crawler #" + cosmicCrawlerCount, Difficulty.getLightEnemyHealth(), Difficulty.getLightEnemyDamage());
        cosmicCrawlerCount++;
        this.size = 1f;
        this.max_velocity = 5;
        // this.max_velocity = 15;
        this.position.x = 1.0f;
        this.position.y = 1.0f;
        // Enemy.getEnemyList().add(this);
    }

    public CosmicCrawler(float x, float y) {
        super("Cosmic Crawler #" + cosmicCrawlerCount, Difficulty.getLightEnemyHealth(), Difficulty.getLightEnemyDamage());
        cosmicCrawlerCount++;
        this.size = 1f;
        this.max_velocity = 5;
        // this.max_velocity = 15;
        this.position.x = x;
        this.position.y = y;
        // Enemy.getEnemyList().add(this);
    }
    
}
