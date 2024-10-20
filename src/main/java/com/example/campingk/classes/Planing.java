package com.example.campingk.classes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class Planing {
    int idCreneau;
    Date dateCreneau;
    Time heureCreneau;
    Time dureeCreneau;
    int placesCreneau;
    String nomAnimation;
    String prenomAnimateur;
    String nomAnimateur;
    String libelleLieu;
    String numRueLieu;
    String nomRueLieu;
    String villeLieu;

    public Planing(int id, Date date, Time heure, Time duree, int places, String animationNom, String animateurPrenom, String animateurNom, String libelle, String numRue, String nomRue, String ville){
        idCreneau = id;
        dateCreneau = date;
        heureCreneau = heure;
        dureeCreneau = duree;
        placesCreneau = places;
        nomAnimation = animationNom;
        prenomAnimateur = animateurPrenom;
        nomAnimateur = animateurNom;
        libelleLieu = libelle;
        numRueLieu = numRue;
        nomRueLieu = nomRue;
        villeLieu = ville;
    }
    @Override
    public String toString() {
        return idCreneau + ": " + nomAnimation + " - dirigé par " + prenomAnimateur + " " + nomAnimateur + " (" + placesCreneau + " places)\n"
                + "le " + dateCreneau + " à " + heureCreneau + " pendant " + dureeCreneau + "\n"
                + libelleLieu + " - " + numRueLieu + " " + nomRueLieu + ", " + villeLieu;
    }
}
