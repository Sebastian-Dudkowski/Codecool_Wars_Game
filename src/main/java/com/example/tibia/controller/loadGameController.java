package com.example.tibia.controller;


import com.example.tibia.DAO.GameDatabaseManager;
import com.example.tibia.Main;
import com.example.tibia.model.GameState;
import com.example.tibia.model.PlayerModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.SQLException;

public class loadGameController {
    GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();

    public static PlayerModel playerModel;
    public static GameState gameState;


    public String playerName;
    @FXML
    private Button mainMenuButton;

    @FXML
    private ListView savesList;

    @FXML
    private Button selectButton;

    @FXML
    void initialize() throws SQLException {
        gameDatabaseManager.setup();
        savesList.getItems().addAll(gameDatabaseManager.getLoadNames());
        savesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                playerName = (savesList.getSelectionModel().getSelectedItem()).toString();
                playerModel = gameDatabaseManager.getSelectedPlayer(playerName);
                gameState = gameDatabaseManager.getGameState(playerModel.getId());
                GameController.isMapLoaded = true;


            }
        });
    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        Main.setMenuView();
    }

    @FXML
    void loadGame(ActionEvent event) {
        Main.setGameView();
    }
}