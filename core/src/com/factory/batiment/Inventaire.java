package com.factory.batiment;

import java.util.HashMap;
import java.util.Map;

public class Inventaire {
    private static Inventaire instance;
    private Map<String, Integer> stocks;

    public Inventaire() {
        this.stocks = new HashMap<>();
    }

    public static Inventaire getInstance() {
        if (instance == null) {
            instance = new Inventaire();
        }
        return instance;
    }

    public void ajouterRessource(String nomRessource, int quantite) {
        stocks.put(nomRessource, stocks.getOrDefault(nomRessource, 0) + quantite);
    }

    public void retirerRessource(String nomRessource, int quantite) {
        stocks.put(nomRessource, stocks.getOrDefault(nomRessource, 0) - quantite);
    }

    public int getQuantiteRessource(String nomRessource) {
        return stocks.getOrDefault(nomRessource, 0);
    }
}
