package com.example.tibia.controller;
import com.example.tibia.HelloApplication;
import com.example.tibia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverController {

    @FXML
    private Button continueButton;

    @FXML
    void continueGame(ActionEvent event) {
        Main.setMenuView();
    }

}



