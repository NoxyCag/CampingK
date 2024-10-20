package com.example.campingk.controllers;

import com.example.campingk.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PlaningController {

    @FXML
    Button boutonAnimateurs;

    @FXML
    Button boutonLieux;

    @FXML
    Button boutonAnimations;


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
}
