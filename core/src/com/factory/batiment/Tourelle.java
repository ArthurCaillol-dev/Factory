package com.factory.batiment;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.factory.enemy.Enemy;

public class Tourelle extends BatimentDefensif {

    private ArrayList<Batiment> batiments;
    private Rectangle hitbox;
    private boolean isFirstTourelle = true;
    private Inventaire inventaire = Inventaire.getInstance();

    public Tourelle(String nom, int hp, int puissanceDeTir, int distanceTir) {
        super("Tourelle", hp, 1, 10);
        if (isFirstTourelle){

        } else {
            if (!deductResourcesForConstruction("Fer", 20)) {
                throw new RuntimeException("Insufficient resources for Tourelle construction");
        }
        } 
    }

    private boolean deductResourcesForConstruction(String nomRessource, int quantite) {
        int currentQuantity = inventaire.getQuantiteRessource(nomRessource);

        if (currentQuantity >= quantite) {
            inventaire.retirerRessource(nomRessource, quantite);
            return true; // Construction successful
        } else {
            return false; // Construction failed due to insufficient resources
        }
    }

    @Override
    public void attaquerEnnemi(Enemy ennemi) {
        if (isInRange(ennemi)) {
            if (ennemi.getHp() > 0) {
                ennemi.receiveDamage(this.puissanceDeTir);
                if (ennemi.getHp() <= 0) {
                    System.out.println("Boum boum");
                }
            }
        }
    }

    @Override
    public void accept(BatimentVisitor visitor) {
        visitor.visit(this);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    public ArrayList<Rectangle> getAllHitboxes() {
        ArrayList<Rectangle> allHitboxes = new ArrayList<>();
        for (Batiment b : batiments) {
            allHitboxes.add(b.getHitbox());
        }
        return allHitboxes;
    }

}
