package com.example.tibia;


import com.example.tibia.EQ.Cell;
import com.example.tibia.EQ.EQLoader;
import com.example.tibia.EQ.EQMap;
import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Inventory;
import com.example.tibia.actors.Player;
import com.example.tibia.controller.HelloController;
import com.example.tibia.items.Item;
import com.example.tibia.items.Key;
import com.example.tibia.map.FieldType;
import com.example.tibia.map.MapLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.example.tibia.map.Field;
import com.example.tibia.map.GameMap;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.example.tibia.controller.GameController;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.Random;

import static com.example.tibia.music.MusicPlayer.*;

public class Main extends Application {
    public static Scene scene;
    public static Stage stage;
    private static FXMLLoader menuViewLoader;
    private static FXMLLoader nameSelectViewLoader;
    private static FXMLLoader gameViewLoader;
    public static int maxHealth;
    public static int allHealth;
    public static int maxMana;
    public static Inventory inventory;
    public static Player player;
    public static GameMap map;
    public static EQMap eq;
    public final int SCREEN_SIZE = 9;
    public static String userName;
    public static String amountHealth;
    public static String amountMana;
    //    Canvas canvas = new Canvas(
//            SCREEN_SIZE * EqTiles.TILE_WIDTH,
//            SCREEN_SIZE * EqTiles.TILE_WIDTH);
//    GraphicsContext context = canvas.getGraphicsContext2D();
//    GraphicsContext contextEQ = canvas.getGraphicsContext2D();
    Canvas canvasEQ = new Canvas(
            SCREEN_SIZE * EqTiles.TILE_WIDTH,
            SCREEN_SIZE * EqTiles.TILE_WIDTH);
    GameController gc;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.alwaysOnTopProperty();
        stage = primaryStage;
        setMenuView();


    }
        private static void setView(FXMLLoader view, String title) {
        try {
            scene = new Scene(view.load());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setMenuView() {
        menuViewLoader = new FXMLLoader(Main.class.getResource("start.fxml"));
        setView(menuViewLoader, "Main Menu");
    }

    public static void setNameSelectView() {
        nameSelectViewLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        setView(nameSelectViewLoader, "Select Name");
    }

    public static void closeView() {
        Main.stage.close();
    }

    public static void setGameView() {
        gameViewLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        setView(gameViewLoader, "Dungeon Crawl");
        ((GameController) gameViewLoader.getController()).setupKeys();
    }

}
