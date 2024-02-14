package com.factory.enemy;

public class VoidstarOverlord extends BossEnemy {

    private static int voidstarOverlordCount = 0;

    public VoidstarOverlord() {
        super("Voidstar Overlord #" + voidstarOverlordCount, Difficulty.getBossEnemyHealth(), Difficulty.getBossEnemyDamage());
        voidstarOverlordCount++;
        this.size = 3;
        this.max_velocity = 2.5f;
        // this.max_velocity = 10;
        this.position.x = 1.0f;
        this.position.y = 1.0f;
        // Enemy.getEnemyList().add(this);
    }

    public VoidstarOverlord(float x, float y) {
        super("Voidstar Overlord #" + voidstarOverlordCount, Difficulty.getBossEnemyHealth(), Difficulty.getBossEnemyDamage());
        voidstarOverlordCount++;
        this.size = 3;
        this.max_velocity = 2.5f;
        // this.max_velocity = 10;
        this.position.x = x;
        this.position.y = y;
        // Enemy.getEnemyList().add(this);
    }
}
