package com.example.tibia;


import com.example.tibia.EQ.EQMap;
import com.example.tibia.actors.Inventory;
import com.example.tibia.actors.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.tibia.map.GameMap;
import javafx.scene.canvas.Canvas;
import com.example.tibia.controller.GameController;

import javax.sound.sampled.Clip;
import java.io.IOException;

import static com.example.tibia.sounds.SoundsPlayer.OPENING;
import static com.example.tibia.sounds.SoundsPlayer.playSound;

public class Main extends Application {
    public static Scene scene;
    public static Stage stage;
    public static Stage stage1;
    private static FXMLLoader menuViewLoader;
    private static FXMLLoader nameSelectViewLoader;
    private static FXMLLoader gameViewLoader;
    public static int maxHealth;
    public static int expNextLvl;
    public static int allHealth;
    public static int maxMana;
    public static Inventory inventory;
    public static Player player;
    public static GameMap map;
    public static int level;
    public static EQMap eq;
    public final int SCREEN_SIZE = 9;
    public static String userName;
    public static String amountHealth;
    public static String amountMana;
    public static Clip clip;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.alwaysOnTopProperty();
        stage = primaryStage;
        clip = playSound(OPENING, (float) 0.2);
        setMenuView();


    }
        private static void setView(FXMLLoader view, String title) {
        try {
            if(stage1!=null){
                stage1.close();
            }
            scene = new Scene(view.load());
            stage.setX(200.0);
            stage.setY(50.0);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void setViewS(FXMLLoader view, String title) {
        try {
            scene = new Scene(view.load());
            stage1 = new Stage();
            stage1.setScene(scene);
            stage1.setTitle(title);
            stage1.show();

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
        clip.stop();
        gameViewLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        setView(gameViewLoader, "Dungeon Crawl");
        ((GameController) gameViewLoader.getController()).setupKeys();
    }

}
