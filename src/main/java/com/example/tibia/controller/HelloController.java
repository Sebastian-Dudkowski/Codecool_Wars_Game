package com.example.tibia.controller;

import com.example.tibia.HelloApplication;
import com.example.tibia.Main;
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

import static com.example.tibia.Main.setGameView;

public class HelloController {

    @FXML
    private Button startButton;

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
