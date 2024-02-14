package com.factory.enemy;

public abstract class Difficulty {
    private static int difficulty = 2;

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int newDifficulty) {
        difficulty = newDifficulty;
    }

    public static int getLightEnemyHealth() {
        switch (difficulty) {
            case 1:
                return 50;

            case 2:
                return 100;
            
            case 3:
                return 200;

            case 4:
                return 500;
        
            default:
                return 0;
        }
    }

    public static int getLightEnemyDamage() {
        switch (difficulty) {
            case 1:
                return 5;

            case 2:
                return 10;
            
            case 3:
                return 20;

            case 4:
                return 50;
        
            default:
                return 0;
        }
    }

    public static int getHeavyEnemeyHealth() {
        switch (difficulty) {
            case 1:
                return 100;

            case 2:
                return 250;
            
            case 3:
                return 500;

            case 4:
                return 1000;
        
            default:
                return 0;
        }
    }

    public static int getHeavyEnemeyDamage() {
        switch (difficulty) {
            case 1:
                return 25;

            case 2:
                return 50;
            
            case 3:
                return 100;

            case 4:
                return 200;
        
            default:
                return 0;
        }
    }

    public static int getBossEnemyHealth() {
        switch (difficulty) {
            case 1:
                return 500;

            case 2:
                return 1000;
            
            case 3:
                return 2000;

            case 4:
                return 5000;
        
            default:
                return 0;
        }
    }

    public static int getBossEnemyDamage() {
        switch (difficulty) {
            case 1:
                return 100;

            case 2:
                return 250;
            
            case 3:
                return 500;

            case 4:
                return 1000;
        
            default:
                return 0;
        }
    }
}
