package com.example.campingk.classes;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class Planing {
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
                + "le " + dateCreneau + " à " + heureCreneau + " pendant " + dureeCreneau + "\n"
                + libelleLieu + " - " + numRueLieu + " " + nomRueLieu + ", " + villeLieu;
    }
}
