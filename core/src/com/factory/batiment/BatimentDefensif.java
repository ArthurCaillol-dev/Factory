package com.factory.batiment;

import com.factory.enemy.Enemy;

public abstract class BatimentDefensif extends Batiment {
    public int puissanceDeTir;
    private float distanceTir;

    public BatimentDefensif(String nom, int hp, int puissanceDeTir, float distanceTir) {
        super(nom, hp);
        this.puissanceDeTir = puissanceDeTir;
        this.distanceTir = distanceTir;
    }

    public int getPuissanceDeTir() {
        return puissanceDeTir;
    }
    public float getDistanceTir() {
        return distanceTir;
    }

    public void attaquerEnnemi(Enemy ennemi){
        if (isInRange(ennemi)) {
            ennemi.setHp(ennemi.getHp() - this.puissanceDeTir);
        }
        else {
            System.out.println("L'ennemi est trop loin");
        }
    }

    public boolean isInRange(Enemy ennemi) {
        float distance = (float) Math.sqrt(Math.pow(ennemi.getX() - this.getX(), 2) + Math.pow(ennemi.getY() - this.getY(), 2));
        return distance <= this.distanceTir;
    }
}

