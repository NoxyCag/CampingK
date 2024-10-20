package com.example.campingk.controllers;

import com.example.campingk.App;
import com.example.campingk.ConnexionBDD;
import com.example.campingk.classes.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AnimationController extends App {

    // Pages
    @FXML
    Button boutonPlaning;

    @FXML
    Button boutonAnimations;

    @FXML
    Button boutonLieux;


    // Modération
    @FXML
    ListView listView;

    @FXML
    TextField textFieldNom;

    @FXML
    TextField textFieldLibelle;

    @FXML
    Button butonAjouter;

    @FXML
    Button butonModifier;

    @FXML
    Button butonSupprimer;

    @FXML
    Button butonAnnuler;


    // autres
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
            String sql = "SELECT numAnimation, nomAnimation, libelleAnimation FROM animation";
            ResultSet resultSet = statement.executeQuery(sql);

            // Effacer les éléments précédents dans la ListView
            listView.getItems().clear();

            // Parcours des résultats et ajout à l'ObservableList
            ObservableList<Animation> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Animation a = new Animation(resultSet.getInt("numAnimation"), resultSet.getString("nomAnimation"), resultSet.getString("libelleAnimation"));
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




    public void allerPageAnimateurs(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Animateurs.fxml"));
        App.lancerPage(fxmlLoader, boutonAnimations);
    }

    public void allerPageLieux(MouseEvent mouseEvent) throws IOException {
        App.lancerPage(new FXMLLoader(App.class.getResource("lieux.fxml")), boutonLieux);
    }

    public void allerPagePlaning(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("planing.fxml"));
        App.lancerPage(fxmlLoader, boutonPlaning);
    }




    public void listViewClicked(MouseEvent mouseEvent) {
        try {
            if(!listView.getSelectionModel().isEmpty()) {
                String[] selectedSplitted = listView.getSelectionModel().getSelectedItem().toString().split(":");
                int numAnimation = Integer.parseInt(selectedSplitted[0]);

                Connection connection = ConnexionBDD.initialiserConnexion();
                Statement statement = connection.createStatement();

                // Exécution de la requête SQL
                String sql = "SELECT nomAnimation, libelleAnimation FROM Animation WHERE numAnimation = '" + numAnimation +"'";
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();

                textFieldNom.setText(resultSet.getString("nomAnimation"));
                textFieldLibelle.setText(resultSet.getString("libelleAnimation"));

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
            int numAnimation = Integer.parseInt(selectedSplitted[0]);

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            // Exécution de la requête SQL
            String sql = "DELETE FROM Animation WHERE numAnimation = '" + numAnimation + "'";
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
            int numAnimation = Integer.parseInt(selectedSplitted[0]);

            String nomAnimation = textFieldNom.getText();
            String libelleAnimation = textFieldLibelle.getText();

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            String sql = "UPDATE Animation SET " +
                    "nomAnimation = '" + nomAnimation +
                    "', libelleAnimation = '" + libelleAnimation +
                    "' WHERE numAnimation = '" + numAnimation + "'";
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
            String nomAnimation = textFieldNom.getText();
            String libelleAnimation = textFieldLibelle.getText();

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO Animation (nomAnimation, libelleAnimation) VALUES(" +
                    "'" + nomAnimation + "" +
                    "', '" + libelleAnimation + "');";
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
        textFieldLibelle.setText("");

        selected = false;

        butonAjouter.setDisable(true);
        butonModifier.setDisable(true);
        butonSupprimer.setDisable(true);
        butonAnnuler.setDisable(true);
    }

    public void changementValeurs(KeyEvent keyEvent) {
        if(!(
                        textFieldNom.getText().equalsIgnoreCase("") &&
                        textFieldLibelle.getText().equalsIgnoreCase(""))
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
