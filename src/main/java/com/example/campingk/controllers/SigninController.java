package com.example.campingk.controllers;

import com.example.campingk.ConnexionBDD;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.campingk.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SigninController {

    @FXML
    Button creerCompte;

    @FXML
    Button retour;

    @FXML
    TextField login;

    @FXML
    PasswordField mdp;

    @FXML
    PasswordField confMdp;

    public void seConnecter(MouseEvent mouseEvent) throws IOException {

        try {
            String mdpText = mdp.getText();
            String[] mdpSplit = mdpText.split("");

            boolean nbCarac = false;
            boolean min = false;
            boolean maj = false;
            boolean chiffre = false;
            boolean caracSpe = false;

            if(mdpSplit.length >= 12) nbCarac = true;

            for (String s : mdpSplit) {
                char c = s.charAt(0);
                if (Character.isLowerCase(c)) min = true;
                if (Character.isUpperCase(c)) maj = true;
                if (Character.isDigit(c)) chiffre = true;
                if (!Character.isLetterOrDigit(c)) caracSpe = true;
            }


            if(mdp.getText().equals(confMdp.getText()) && !login.getText().isEmpty() && nbCarac && min && maj && chiffre && caracSpe) {

                Connection conn = ConnexionBDD.initialiserConnexion();

                if (conn != null) {
                    try {
                        // Préparer la requête d'insertion
                        String sql = "INSERT INTO connection (idConnection, login, mdp) VALUES (NULL, ?, ?)";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setString(1, login.getText());
                        statement.setString(2, mdp.getText());

                        // Exécuter la requête
                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            // Redirection vers la page de connexion si l'insertion a réussi
                            App.lancerPage(new FXMLLoader(App.class.getResource("login.fxml")), creerCompte);
                        }
                    } catch (SQLException ex) {
                        // Gérer les erreurs SQL
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur SQL");
                        alert.setContentText("Impossible de créer le compte : " + ex.getMessage());
                        alert.showAndWait();
                    } finally {
                        try {
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    login.setText("");
                    mdp.setText("");
                    confMdp.setText("");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Impossible de créer le compte");
                    alert.showAndWait();
                }
            } else {
                login.setText("");
                mdp.setText("");
                confMdp.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Impossible de créer le compte");
                alert.showAndWait();
            }

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Impossible de créer le compte");
            alert.showAndWait();
        }
    }

    public void allerPageLogin(MouseEvent mouseEvent) throws IOException {
        FXMLLoader page = new FXMLLoader(App.class.getResource("login.fxml"));
        App.lancerPage(page, retour);
    }
}
