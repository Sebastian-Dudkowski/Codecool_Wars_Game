package com.example.tibia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label playerName;


    public void initialize() {
        System.out.println(HelloController.userName);
        playerName.setText(HelloController.userName);
    }


}
