package com.factory.batiment;

public abstract class BatimentRessource extends Batiment {
    private int quantiteRessource;

    public BatimentRessource(String nom, int hp) {
        super(nom, hp);
        quantiteRessource = 0;
    }

    public int getQuantiteRessource() {
        return quantiteRessource;
    }

    public void collecterRessource(int quantite) {
        quantiteRessource += quantite;
    }
}

