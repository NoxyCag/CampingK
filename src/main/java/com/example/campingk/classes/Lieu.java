package com.example.campingk.classes;

public class Lieu {
    int idLieu;
    String libelleLieu;
    String numRueLieu;
    String nomRueLieu;
    String villeLieu;

    public Lieu(int id, String libelle, String numRue, String nomRue, String ville) {
        idLieu = id;
        libelleLieu = libelle;
        numRueLieu = numRue;
        nomRueLieu = nomRue;
        villeLieu = ville;
    }

    @Override
    public String toString() {
        return idLieu + ": " + libelleLieu + " (" + numRueLieu + " " + nomRueLieu + ", " + villeLieu + ")";
    }
}
