package com.example.tibia.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;

public class GameController {

    @FXML
    private HBox hbox;

    @FXML
    private Label playerName;

    public Label getPlayerName() {
        return playerName;
    }

    public void setPlayerName(Label playerName) {
        this.playerName = playerName;
    }

    @FXML
    private Button pickUpButton;
    @FXML
    private Canvas canvas = new Canvas(576, 576);




@FXML
    private VBox playerMenu;

    @FXML
    private BorderPane borderPaneEQ;

    public BorderPane getBorderPaneEQ() {
        return borderPaneEQ;
    }
  @FXML
    private ProgressBar progressHealth;

    public ProgressBar getProgressHealth() {
        return progressHealth;
    }

    @FXML
    private ProgressBar progressMana;

    public ProgressBar getProgressMana() {
        return progressMana;
    }

    @FXML
    private Canvas canvasEQ;

    public Canvas getCanvasEQ() {
        return canvasEQ;
    }

    public Button getPickUpButton() {
        return pickUpButton;
    }

    @FXML
    private BorderPane borderpane;
    public Canvas getCanvas() {
        return canvas;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }


}

