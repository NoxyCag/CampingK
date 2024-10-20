package com.example.campingk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1456, 816);


        stage.setTitle("CampingK");
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initStyle(StageStyle.DECORATED); // Utilise le style de fenêtre par défaut


        stage.setScene(scene);
        stage.show();
    }

    public static void lancerPage(FXMLLoader fxmlLoader, Button btn) {
        try {

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1456, 816);

            Stage stage = new Stage();
            stage.setTitle("CampingK");
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initStyle(StageStyle.DECORATED); // Utilise le style de fenêtre par défaut

            stage.setScene(scene);
            stage.show();

            // Fermer la fenêtre de connexion
            Stage currentStage = (Stage) btn.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lancement(){

    };


    public static void main(String[] args) {
        launch();
    }
}