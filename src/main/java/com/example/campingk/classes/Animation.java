package com.example.campingk.classes;

public class Animation {
    int numAnimation;
    String nomAnimation;
    String libelleAnimation;

    public Animation(int num, String nom, String libelle) {
        numAnimation = num;
        nomAnimation = nom;
        libelleAnimation = libelle;
    }

    @Override
    public String toString() {
        return numAnimation + ": " + nomAnimation + " - " + libelleAnimation;
    }
}
