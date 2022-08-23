package com.example.tibia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController {

    @FXML
    private ImageView field00;

    @FXML
    private GridPane gpBoard;

    @FXML
    private Label playerName;


    public void initialize() {
        System.out.println(HelloController.getUserName());
        playerName.setText(HelloController.getUserName());
        gpBoard.add(new ImageView("C:\\Users\\PC\\Desktop\\motors-v5.png"), 7,7);

    }


}
