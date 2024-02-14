package com.factory.enemy;

public interface EnemyVisitor {
    void visit(CosmicCrawler cosmicCrawler);
    void visit(ExoplanetEnforcer enforcer);
    void visit(VoidstarOverlord voidstarOverlord);
}
