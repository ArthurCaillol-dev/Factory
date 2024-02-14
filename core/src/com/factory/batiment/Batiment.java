package com.factory.batiment;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;

public abstract class Batiment {
    private String nom;
    private int hp;
    public float x;
    public float y;
    private ArrayList<Batiment> batiments;
    private Rectangle hitbox;

    public Batiment() {
        this.batiments = new ArrayList<Batiment>();
        this.hitbox = new Rectangle();
    }

    public Batiment(String nom, int hp) {
        this.nom = nom;
        this.hp = hp;
        this.batiments = new ArrayList<Batiment>();
    }

    public String getNom() {
        return nom;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void receiveDamage(int damage) {
        this.setHp(this.getHp() - damage);

        if (this.getHp() < 0) {
            this.setHp(0);
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public ArrayList<Batiment> getBatiments() {
        return this.batiments;
    }

    public boolean removeBatiment(Batiment building) {
        if (this.batiments.contains(building)) {
            this.batiments.remove(building);
            return true;
        }

        return false;
    }

    public boolean removeBatiment(List<Batiment> buildings) {
        if (this.batiments.removeAll(buildings)) {
            return true;
        }
        
        return false;
    }

    public abstract void accept(BatimentVisitor visitor);

    // Dans la classe Batiment
    public boolean ajouterBatiment(Batiment batiment) {
        if (batiment instanceof Base) {
            Base baseToAdd = (Base) batiment;
            if (emplacementEstValide(baseToAdd, baseToAdd.getX(), baseToAdd.getY())) {
                batiments.add(baseToAdd);
                // Autres actions spécifiques à Base
                System.out.println("Base ajoutée avec succès");
                return true;
            } else {
                System.out.println("Emplacement invalide pour la base à ajouter.");
                return false;
            }
        } else if (batiment instanceof Tourelle) {
            Tourelle tourelleToAdd = (Tourelle) batiment;
            if (emplacementEstValide(tourelleToAdd, tourelleToAdd.getX(), tourelleToAdd.getY())) {
                batiments.add(tourelleToAdd);
                System.out.println("Tourelle ajoutée avec succès");
                return true;
            } else {
                System.out.println("Emplacement invalide pour la tourelle à ajouter.");
                return false;
            }
        } else if (batiment instanceof Foreuse){
            Foreuse foreuseToAdd = (Foreuse) batiment;
            if (emplacementEstValide(foreuseToAdd, foreuseToAdd.getX(),foreuseToAdd.getY())) {
                batiments.add(foreuseToAdd);
                System.out.println("Tourelle ajoutée avec succès");
                return true;
            } else {
                System.out.println("Emplacement invalide pour la tourelle à ajouter.");
                return false;
            }
        } else {
            System.out.println("Type de bâtiment non pris en charge.");
            return false;
        }
    }
    public boolean emplacementEstValide(Batiment batiment, float x, float y) {
        for (Batiment b : batiments) {
            if (b.getX() == x && b.getY() == y) {
                System.out.println("Emplacement occupé par un autre bâtiment");
                return false;
            }
        }
        return true;
    }

    public void supprimerBatiment(Batiment batiment) {
        if (batiments.contains(batiment)) {
            batiments.remove(batiment);
            System.out.println("Batiment supprimé avec succès");
        } else {
            System.out.println("Le batiment n'existe pas dans cet emplacement");
        }
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
