package com.example.tibia.controller;

import com.example.tibia.Tiles;
import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;
import com.example.tibia.map.GameMap;
import com.example.tibia.map.MapLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController {
    // TODO: Create player
    private Player player = new Player(HelloController.getUserName(), null);
    GameMap map = MapLoader.loadMap(player);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    @FXML
    private ImageView field00;

    @FXML
    private GridPane gpBoard;

    @FXML
    private Label playerName;


    public void initialize() {
        System.out.println(HelloController.getUserName());
        playerName.setText(HelloController.getUserName());
//        gpBoard.add(new ImageView("C:\\Users\\PC\\Desktop\\motors-v5.png"), 7,7);

    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Field field = map.getField(x, y);
                if (field.getActor() != null) {
                    Tiles.drawTile(context, field, x, y);
                } else {
                    Tiles.drawTile(context, field, x, y);
                }
            }
        }
//        healthLabel.setText("" + map.getPlayer().getHealth());
    }


}
