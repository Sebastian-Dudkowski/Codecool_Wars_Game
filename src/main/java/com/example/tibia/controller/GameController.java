package com.example.tibia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class GameController {

    @FXML
    private HBox hbox;

    @FXML
    private Label playerName;


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

