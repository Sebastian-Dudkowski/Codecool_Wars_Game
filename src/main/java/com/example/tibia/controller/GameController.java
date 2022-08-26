package com.example.tibia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GameController {

    @FXML
    private HBox hbox;

    @FXML
    private Label playerName;

    @FXML
    private GridPane swordField;

    @FXML
    private GridPane healthPotionField;

    @FXML
    private GridPane helmetField;

    @FXML
    private GridPane armorField;

    @FXML
    private GridPane shoesField;

    @FXML
    private GridPane cardField;

    @FXML
    private GridPane shieldField;

    @FXML
    private GridPane manaPotionField;

    @FXML
    private Button pickUpButton;
    @FXML
    private Canvas canvas = new Canvas(576, 576);

    @FXML
    void pick(ActionEvent event) {

    }
    @FXML
    private BorderPane borderpane = new BorderPane();
    public Canvas getCanvas() {
        return canvas;
    }
    public BorderPane getBorderpane() {
        return borderpane;
    }

}

