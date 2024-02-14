package com.factory.batiment;

public class Base extends Batiment {
    private int ressources;
    private int width = 4;
    private int height = 4;

    public Base(String name, int hp) {
        super(name, hp);
        this.ressources = 0; // Initialisation des ressources à zéro
    }

    public int getRessources() {
        return ressources;
    }

    public void recupRessources(int quantite) {
        // Vous devez définir la logique de récupération des ressources ici
        // Par exemple, vous pouvez augmenter le montant de ressources en fonction de la quantité passée en argument
        ressources += quantite;
    }
    @Override
    public void accept(BatimentVisitor visitor) {
        visitor.visit(this);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}