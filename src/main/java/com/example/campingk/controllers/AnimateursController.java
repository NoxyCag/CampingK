package com.example.campingk.controllers;

import com.example.campingk.App;
import com.example.campingk.ConnexionBDD;
import com.example.campingk.classes.Animateur;
import com.example.campingk.classes.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AnimateursController {

    // Pages
    @FXML
    Button boutonPlaning;

    @FXML
    Button boutonLieux;

    @FXML
    Button boutonAnimations;


    // Modération
    @FXML
    ListView listView;

    @FXML
    TextField textFieldNom;

    @FXML
    TextField textFieldPrenom;

    @FXML
    TextField textFieldPays;

    @FXML
    TextField textFieldVille;

    @FXML
    TextField textFieldNumRue;

    @FXML
    TextField textFieldNomRue;

    @FXML
    TextField textFieldEmail;

    @FXML
    Button butonAjouter;

    @FXML
    Button butonModifier;

    @FXML
    Button butonSupprimer;

    @FXML
    Button butonAnnuler;


    // Autres
    boolean selected = false;



    @FXML
    public void initialize() {
        majListView();
        butonAjouter.setDisable(true);
        butonModifier.setDisable(true);
        butonSupprimer.setDisable(true);
        butonAnnuler.setDisable(true);
    }


    public void majListView() {
        try {
            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            // Exécution de la requête SQL
            String sql = "SELECT numAnimateur, nomAnimateur, prenomAnimateur, emailAnimateur FROM animateur";
            ResultSet resultSet = statement.executeQuery(sql);

            // Effacer les éléments précédents dans la ListView
            listView.getItems().clear();

            // Parcours des résultats et ajout à l'ObservableList
            ObservableList<Animateur> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Animateur a = new Animateur(
                        resultSet.getInt("numAnimateur"),
                        resultSet.getString("nomAnimateur"),
                        resultSet.getString("prenomAnimateur"),
                        resultSet.getString("emailAnimateur"));
                data.add(a);
            }

            // Mise à jour de la ListView avec les nouvelles données
            listView.setItems(data);

            // Fermeture des ressources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void allerPagePlaning(MouseEvent mouseEvent) throws IOException {
        FXMLLoader page = new FXMLLoader(App.class.getResource("planing.fxml"));
        App.lancerPage(page, boutonPlaning);
    }

    public void allerPageLieux(MouseEvent mouseEvent) throws IOException {
        FXMLLoader page = new FXMLLoader(App.class.getResource("lieux.fxml"));
        App.lancerPage(page, boutonLieux);
    }

    public void allerPageAnimations(MouseEvent mouseEvent) throws IOException {
        FXMLLoader page = new FXMLLoader(App.class.getResource("animations.fxml"));
        App.lancerPage(page, boutonAnimations);
    }




    public void listViewClicked(MouseEvent mouseEvent) {
        try {
            if(!listView.getSelectionModel().isEmpty()) {
                String[] selectedSplitted = listView.getSelectionModel().getSelectedItem().toString().split(":");
                int numAnimateur = Integer.parseInt(selectedSplitted[0]);

                Connection connection = ConnexionBDD.initialiserConnexion();
                Statement statement = connection.createStatement();

                // Exécution de la requête SQL
                String sql = "SELECT nomAnimateur, prenomAnimateur, paysAnimateur, villeAnimateur, numRueAnimateur, nomRueAnimateur, emailAnimateur FROM animateur WHERE numAnimateur = '" + numAnimateur +"'";
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();

                textFieldNom.setText(resultSet.getString("nomAnimateur"));
                textFieldPrenom.setText(resultSet.getString("prenomAnimateur"));
                textFieldPays.setText(resultSet.getString("paysAnimateur"));
                textFieldVille.setText(resultSet.getString("villeAnimateur"));
                textFieldNumRue.setText(resultSet.getString("numRueAnimateur"));
                textFieldNomRue.setText(resultSet.getString("nomRueAnimateur"));
                textFieldEmail.setText(resultSet.getString("emailAnimateur"));

                selected = true;

                butonAjouter.setDisable(true);
                butonModifier.setDisable(false);
                butonSupprimer.setDisable(false);
                butonAnnuler.setDisable(false);

                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void supprimerClicked(MouseEvent mouseEvent) {
        try {
            String[] selectedSplitted = listView.getSelectionModel().getSelectedItem().toString().split(":");
            int numAnimateur = Integer.parseInt(selectedSplitted[0]);

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            // Exécution de la requête SQL
            String sql = "DELETE FROM animateur WHERE numAnimateur = '" + numAnimateur + "'";
            statement.execute(sql);

            statement.close();
            connection.close();

            majListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierClicked(MouseEvent mouseEvent) {
        try {
            String[] selectedSplitted = listView.getSelectionModel().getSelectedItem().toString().split(":");
            int numAnimateur = Integer.parseInt(selectedSplitted[0]);
            String nomAnimateur = textFieldNom.getText();
            String prenomAnimateur = textFieldPrenom.getText();
            String paysAnimateur = textFieldPays.getText();
            String villeAnimateur = textFieldVille.getText();
            String numRueAnimateur = textFieldNumRue.getText();
            String nomRueAnimateur = textFieldNomRue.getText();
            String emailAnimateur = textFieldEmail.getText();

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            String sql = "UPDATE Animateur SET " +
                    "nomAnimateur = '" + nomAnimateur +
                    "', prenomAnimateur = '" + prenomAnimateur +
                    "', paysAnimateur = '" + paysAnimateur +
                    "', villeAnimateur = '" + villeAnimateur +
                    "', numRueAnimateur = '" + numRueAnimateur +
                    "', nomRueAnimateur = '" + nomRueAnimateur +
                    "', emailAnimateur = '" + emailAnimateur +
                    "' WHERE numAnimateur = '" + numAnimateur + "'";
            statement.execute(sql);

            statement.close();
            connection.close();

            majListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterClicked(MouseEvent mouseEvent) {
        try {
            String nomAnimateur = textFieldNom.getText();
            String prenomAnimateur = textFieldPrenom.getText();
            String paysAnimateur = textFieldPays.getText();
            String villeAnimateur = textFieldVille.getText();
            String numRueAnimateur = textFieldNumRue.getText();
            String nomRueAnimateur = textFieldNomRue.getText();
            String emailAnimateur = textFieldEmail.getText();

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO Animateur (nomAnimateur, prenomAnimateur, paysAnimateur, villeAnimateur, numRueAnimateur, nomRueAnimateur, emailAnimateur) VALUES(" +
                    "'" + nomAnimateur + "" +
                    "', '" + prenomAnimateur +
                    "', '" + paysAnimateur +
                    "', '" + villeAnimateur +
                    "', '" + numRueAnimateur +
                    "', '" + nomRueAnimateur +
                    "', '" + emailAnimateur + "');";
            statement.execute(sql);

            statement.close();
            connection.close();

            majListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void annulerClicked(MouseEvent mouseEvent) {
        listView.getSelectionModel().clearSelection();

        textFieldNom.setText("");
        textFieldPrenom.setText("");
        textFieldPays.setText("");
        textFieldVille.setText("");
        textFieldNumRue.setText("");
        textFieldNomRue.setText("");
        textFieldEmail.setText("");

        selected = false;

        butonAjouter.setDisable(true);
        butonModifier.setDisable(true);
        butonSupprimer.setDisable(true);
        butonAnnuler.setDisable(true);
    }

    public void changementValeurs(KeyEvent keyEvent) {
        if(!(
            textFieldNom.getText().equalsIgnoreCase("") &&
            textFieldPrenom.getText().equalsIgnoreCase("") &&
            textFieldPays.getText().equalsIgnoreCase("") &&
            textFieldVille.getText().equalsIgnoreCase("") &&
            textFieldNumRue.getText().equalsIgnoreCase("") &&
            textFieldNomRue.getText().equalsIgnoreCase("") &&
            textFieldEmail.getText().equalsIgnoreCase(""))
        ) {
            butonAjouter.setDisable(false);
            if(selected) {
                butonModifier.setDisable(false);
            }
        } else {
            butonAjouter.setDisable(true);
            if(selected) {
                butonModifier.setDisable(true);
            }
        }

        butonSupprimer.setDisable(true);
        butonAnnuler.setDisable(false);
    }
}
