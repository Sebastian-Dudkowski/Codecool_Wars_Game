package com.example.tibia.controller;

import com.example.tibia.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button startButton;

    @FXML
    private TextField textFieldName;

    public static String getUserName() {
        return userName;
    }

    @FXML
    void startNewGame(ActionEvent event) throws IOException {
        getNewGame();
    }

    @FXML
    void startNewGameKeyboard(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            getNewGame();
        }
    }
    public static String userName;


    public void getNewGame () throws IOException {


        Stage stageToClose = (Stage) textFieldName.getScene().getWindow();
        stageToClose.close();
    }



}
