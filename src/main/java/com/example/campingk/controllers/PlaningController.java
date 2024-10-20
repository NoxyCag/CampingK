package com.example.campingk.controllers;

import com.example.campingk.App;
import com.example.campingk.ConnexionBDD;
import com.example.campingk.classes.Animation;
import com.example.campingk.classes.Planing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlaningController {

    // Pages
    @FXML
    Button boutonAnimateurs;

    @FXML
    Button boutonLieux;

    @FXML
    Button boutonAnimations;


    // Modération
    @FXML
    ListView listView;

    @FXML
    TextField textFieldDuree;

    @FXML
    TextField textFieldHeure;

    @FXML
    DatePicker datePickerDate;

    @FXML
    Spinner spinnerNombrePlaces;

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
            String sql = "SELECT\n" +
                    "creneau.idCreneau, creneau.dateCreneau, creneau.heureCreneau, creneau.dureeCreneau, creneau.placesCreneau,\n" +
                    "animation.nomAnimation,\n" +
                    "animateur.prenomAnimateur, animateur.nomAnimateur,\n" +
                    "lieu.libelleLieu, lieu.numRueLieu, lieu.nomRueLieu, lieu.villeLieu\n" +
                    "\n" +
                    "FROM `creneau` \n" +
                    "INNER JOIN animation ON creneau.idAnimation = animation.numAnimation\n" +
                    "INNER JOIN animer ON creneau.idCreneau = animer.idCreneau \n" +
                    "INNER JOIN animateur ON animer.numAnimateur = animateur.numAnimateur \n" +
                    "INNER JOIN lieu ON creneau.idLieu = lieu.idLieu;";
            ResultSet resultSet = statement.executeQuery(sql);

            // Effacer les éléments précédents dans la ListView
            listView.getItems().clear();

            // Parcours des résultats et ajout à l'ObservableList
            ObservableList<Planing> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Planing p = new Planing(
                        resultSet.getInt("idCreneau"),
                        resultSet.getDate("dateCreneau"),
                        resultSet.getTime("heureCreneau"),
                        resultSet.getTime("dureeCreneau"),
                        resultSet.getInt("placesCreneau"),
                        resultSet.getString("nomAnimation"),
                        resultSet.getString("prenomAnimateur"),
                        resultSet.getString("nomAnimateur"),
                        resultSet.getString("libelleLieu"),
                        resultSet.getString("numRueLieu"),
                        resultSet.getString("nomRueLieu"),
                        resultSet.getString("villeLieu"));
                data.add(p);
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("animateurs.fxml"));
        App.lancerPage(fxmlLoader, boutonAnimateurs);
    }

    public void allerPageLieux(MouseEvent mouseEvent) throws IOException {
        App.lancerPage(new FXMLLoader(App.class.getResource("lieux.fxml")), boutonLieux);
    }

    public void allerPageAnimations(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("animations.fxml"));
        App.lancerPage(fxmlLoader, boutonAnimations);
    }





    public void listViewClicked(MouseEvent mouseEvent) {
        try {
            if(!listView.getSelectionModel().isEmpty()) {
                String[] selectedSplitted = listView.getSelectionModel().getSelectedItem().toString().split(":");
                int idCreneau = Integer.parseInt(selectedSplitted[0]);

                Connection connection = ConnexionBDD.initialiserConnexion();
                Statement statement = connection.createStatement();

                // Exécution de la requête SQL
                String sql = "SELECT dureeCreneau, heureCreneau FROM Creneau WHERE idCreneau = '" + idCreneau +"'";
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();

                textFieldDuree.setText(resultSet.getString("dureeCreneau"));
                textFieldHeure.setText(resultSet.getString("heureCreneau"));

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




    public void annulerClicked(MouseEvent mouseEvent) {
        listView.getSelectionModel().clearSelection();

        textFieldDuree.setText("");
        textFieldHeure.setText("");

        selected = false;

        butonAjouter.setDisable(true);
        butonModifier.setDisable(true);
        butonSupprimer.setDisable(true);
        butonAnnuler.setDisable(true);
    }

    public void changementValeurs(KeyEvent keyEvent) {
        if(!(
                textFieldDuree.getText().equalsIgnoreCase("") &&
                        textFieldHeure.getText().equalsIgnoreCase(""))
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



    public void supprimerClicked(MouseEvent mouseEvent) {
        try {
            String[] selectedSplitted = listView.getSelectionModel().getSelectedItem().toString().split(":");
            int idCreneau = Integer.parseInt(selectedSplitted[0]);

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            // Exécution de la requête SQL
            String sql = "DELETE FROM Creneau WHERE idCreneau = '" + idCreneau + "'";
            statement.execute(sql);

            statement.close();
            connection.close();

            majListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
