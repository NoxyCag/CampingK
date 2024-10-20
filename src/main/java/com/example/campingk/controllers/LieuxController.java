package com.example.campingk.controllers;

import com.example.campingk.App;
import com.example.campingk.ConnexionBDD;
import com.example.campingk.classes.Animateur;
import com.example.campingk.classes.Lieu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class LieuxController {

    // Pages
    @FXML
    Button btnPlaning;

    @FXML
    Button btnAnimateurs;

    @FXML
    Button btnAnimations;


    // Modération
    @FXML
    ListView listView;

    @FXML
    TextField textFieldLibelle;

    @FXML
    TextField textFieldPays;

    @FXML
    TextField textFieldVille;

    @FXML
    TextField textFieldNumRue;

    @FXML
    TextField textFieldNomRue;

    @FXML
    TextField textFieldCoordonnees;

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
            String sql = "SELECT idLieu, libelleLieu, numRueLieu, nomRueLieu, villeLieu FROM lieu";
            ResultSet resultSet = statement.executeQuery(sql);

            // Effacer les éléments précédents dans la ListView
            listView.getItems().clear();

            // Parcours des résultats et ajout à l'ObservableList
            ObservableList<Lieu> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Lieu l = new Lieu(resultSet.getInt("idLieu"), resultSet.getString("libelleLieu"), resultSet.getString("numRueLieu"), resultSet.getString("nomRueLieu"), resultSet.getString("villeLieu"));
                data.add(l);
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
        App.lancerPage(new FXMLLoader(App.class.getResource("planing.fxml")), btnPlaning);
    }

    public void allerPageAnimateurs(MouseEvent mouseEvent) throws IOException  {
        App.lancerPage(new FXMLLoader(App.class.getResource("animateurs.fxml")), btnAnimateurs);
    }

    public void allerPageAnimations(MouseEvent mouseEvent) throws IOException  {
        App.lancerPage(new FXMLLoader(App.class.getResource("animations.fxml")), btnAnimations);
    }




    public void listViewClicked(MouseEvent mouseEvent) {
        try {
            if(!listView.getSelectionModel().isEmpty()) {
                String[] selectedSplitted = listView.getSelectionModel().getSelectedItem().toString().split(":");
                int idLieu = Integer.parseInt(selectedSplitted[0]);

                Connection connection = ConnexionBDD.initialiserConnexion();
                Statement statement = connection.createStatement();

                // Exécution de la requête SQL
                String sql = "SELECT libelleLieu, paysLieu, villeLieu, numRueLieu, nomRueLieu, coordonneesLieu FROM Lieu WHERE idLieu = '" + idLieu +"'";
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();

                textFieldLibelle.setText(resultSet.getString("libelleLieu"));
                textFieldPays.setText(resultSet.getString("paysLieu"));
                textFieldVille.setText(resultSet.getString("villeLieu"));
                textFieldNumRue.setText(resultSet.getString("numRueLieu"));
                textFieldNomRue.setText(resultSet.getString("nomRueLieu"));
                textFieldCoordonnees.setText(resultSet.getString("coordonneesLieu"));

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
            int idLieu = Integer.parseInt(selectedSplitted[0]);

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            // Exécution de la requête SQL
            String sql = "DELETE FROM Lieu WHERE idLieu = '" + idLieu + "'";
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
            int idLieu = Integer.parseInt(selectedSplitted[0]);
            String libelleLieu = textFieldLibelle.getText();
            String paysLieu = textFieldPays.getText();
            String villeLieu = textFieldVille.getText();
            String numRueLieu = textFieldNumRue.getText();
            String nomRueLieu = textFieldNomRue.getText();
            String coordonneesLieu = textFieldCoordonnees.getText();

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            String sql = "UPDATE Lieu SET " +
                    "libelleLieu = '" + libelleLieu +
                    "', paysLieu = '" + paysLieu +
                    "', villeLieu = '" + villeLieu +
                    "', numRueLieu = '" + numRueLieu +
                    "', nomRueLieu = '" + nomRueLieu +
                    "', coordonneesLieu = '" + coordonneesLieu +
                    "' WHERE idLieu = '" + idLieu + "'";
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
            String libelleLieu = textFieldLibelle.getText();
            String paysLieu = textFieldPays.getText();
            String villeLieu = textFieldVille.getText();
            String numRueLieu = textFieldNumRue.getText();
            String nomRueLieu = textFieldNomRue.getText();
            String coordonneesLieu = textFieldCoordonnees.getText();

            Connection connection = ConnexionBDD.initialiserConnexion();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO Lieu (libelleLieu, paysLieu, villeLieu, numRueLieu, nomRueLieu, coordonneesLieu) VALUES(" +
                    "'" + libelleLieu + "" +
                    "', '" + paysLieu +
                    "', '" + villeLieu +
                    "', '" + numRueLieu +
                    "', '" + nomRueLieu +
                    "', '" + coordonneesLieu + "');";
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

        textFieldLibelle.setText("");
        textFieldPays.setText("");
        textFieldVille.setText("");
        textFieldNumRue.setText("");
        textFieldNomRue.setText("");
        textFieldCoordonnees.setText("");

        selected = false;

        butonAjouter.setDisable(true);
        butonModifier.setDisable(true);
        butonSupprimer.setDisable(true);
        butonAnnuler.setDisable(true);
    }

    public void changementValeurs(KeyEvent keyEvent) {
        if(!(
                textFieldLibelle.getText().equalsIgnoreCase("") &&
                        textFieldPays.getText().equalsIgnoreCase("") &&
                        textFieldVille.getText().equalsIgnoreCase("") &&
                        textFieldNumRue.getText().equalsIgnoreCase("") &&
                        textFieldNomRue.getText().equalsIgnoreCase("") &&
                        textFieldCoordonnees.getText().equalsIgnoreCase(""))
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
