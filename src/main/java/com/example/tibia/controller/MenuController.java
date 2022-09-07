package com.example.tibia.controller;

import com.example.tibia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button buttonStartGame;

    @FXML
    private Button buttonLoadGame;

    @FXML
    private Button buttonExit;

    @FXML
    void exit(ActionEvent event) {
        Main.closeView();

    }

    @FXML
    void loadGame(ActionEvent event) {

    }

    @FXML
    void startGame(ActionEvent event) throws IOException {

        Main.setNameSelectView();

    }

}
