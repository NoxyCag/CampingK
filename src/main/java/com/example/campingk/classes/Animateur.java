package com.example.campingk.classes;

public class Animateur {
    int numAnimateur;
    String nomAnimateur;
    String prenomAnimateur;
    String email;

    public Animateur(int num, String nom, String prenom, String mail) {
        numAnimateur = num;
        nomAnimateur = nom;
        prenomAnimateur = prenom;
        email = mail;
    }

    @Override
    public String toString(){
        return (numAnimateur + ": " + nomAnimateur + " " + prenomAnimateur + " " +"("+ email + ")");
    }
}
