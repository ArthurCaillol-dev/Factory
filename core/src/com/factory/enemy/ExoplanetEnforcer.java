package com.factory.enemy;

public class ExoplanetEnforcer extends HeavyEnemy {

    private static int exoplanetEnforcerCount = 0;

    public ExoplanetEnforcer() {
        super("Exoplanet Enforcer #" + exoplanetEnforcerCount, Difficulty.getHeavyEnemeyHealth(), Difficulty.getHeavyEnemeyDamage());
        exoplanetEnforcerCount++;
        this.size = 2;
        this.max_velocity = 3.5f;
        // this.max_velocity = 12;
        this.position.x = 1.0f;
        this.position.y = 1.0f;
        // Enemy.getEnemyList().add(this);
    }
    
    public ExoplanetEnforcer(float x, float y) {
        super("Exoplanet Enforcer #" + exoplanetEnforcerCount, Difficulty.getHeavyEnemeyHealth(), Difficulty.getHeavyEnemeyDamage());
        exoplanetEnforcerCount++;
        this.size = 2;
        this.max_velocity = 3.5f;
        // this.max_velocity = 12;
        this.position.x = x;
        this.position.y = y;
        // Enemy.getEnemyList().add(this);
    }
}
