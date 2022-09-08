package com.example.tibia.controller;

import com.example.tibia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class HelloController {

    @FXML
    public TextField textFieldName;

    public static String getUserName() {
        return userName;
    }

    @FXML
    void startNewGame(ActionEvent event) throws IOException {
        userName = textFieldName.getText();
        Main.setGameView();
    }

    @FXML
    void startNewGameKeyboard(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            userName = textFieldName.getText();
            Main.setGameView();
        }
    }
    public static String userName;



}
