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

    public static String getUserName() {
        return userName;
    }

    private static String userName;

    public void getNewGame () throws IOException {
        userName = textFieldName.getText();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Stage stageToClose = (Stage) textFieldName.getScene().getWindow();
        stageToClose.close();
    }

}