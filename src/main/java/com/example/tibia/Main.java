package com.example.tibia;


import com.example.tibia.EQ.Cell;
import com.example.tibia.EQ.EQLoader;
import com.example.tibia.EQ.EQMap;
import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Inventory;
import com.example.tibia.actors.Player;
import com.example.tibia.controller.HelloController;
import com.example.tibia.items.ItemName;
import com.example.tibia.map.MapLoader;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import com.example.tibia.map.Field;
import com.example.tibia.map.GameMap;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.example.tibia.controller.GameController;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static com.example.tibia.music.MusicPlayer.*;

public class Main extends Application {
    private int maxHealth;
    private int maxMana;
    private Inventory inventory = new Inventory();
    private Player player = new Player(HelloController.getUserName(), null, 100, 100, 35);
    private GameMap map = MapLoader.loadMap(player);
    private EQMap eq = EQLoader.loadEQ(player);
    private final int SCREEN_SIZE = 9;
    private String userName;
    Canvas canvas = new Canvas(
            SCREEN_SIZE * Tiles.TILE_WIDTH,
            SCREEN_SIZE * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    GraphicsContext contextEQ = canvas.getGraphicsContext2D();
    Canvas canvasEQ = new Canvas(
            SCREEN_SIZE * Tiles.TILE_WIDTH,
            SCREEN_SIZE * Tiles.TILE_WIDTH);
    GameController gc;

    public static void main(String[] args) {
        launch(args);
    }

    public void printMenu() {
        try {
//            playSound(opening, (float) 0.4);
            Stage stage = new Stage();
            stage.setResizable(false);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene2);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        printMenu();
        userName = HelloController.getUserName();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 650);
        gc = fxmlLoader.getController();
        gc.getPlayerName().setText(HelloController.getUserName());
        contextEQ = gc.getCanvasEQ().getGraphicsContext2D();
        context = gc.getCanvas().getGraphicsContext2D();
        gc.getBorderPaneEQ().setCenter(gc.getCanvasEQ());
        gc.getBorderpane().setCenter(gc.getCanvas());
        maxHealth = player.getHealth();
        maxMana = player.getMana();
        displayEQ();
        gc.getPickUpButton().addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            inventory.addItem(map.getPlayer().getField().getItem());
            map.getPlayer().getField().setItem(null);
            displayEQ();
        });
        primaryStage.setScene(scene);
        displayMap();
        startNpcMovement();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        playSound(opening, (float) 0.4);

    }

    void pickUp() {
        if (map.getPlayer().getField().getItem() != null) {
            gc.getPickUpButton().setDisable(false);
        } else {
            gc.getPickUpButton().setDisable(true);
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
            case UP:
                map.getPlayer().move(0, -1);
                displayMap();
                break;
            case S:
            case DOWN:
                map.getPlayer().move(0, 1);
                displayMap();
                break;
            case A:
            case LEFT:
                map.getPlayer().move(-1, 0);
                displayMap();
                break;
            case D:
            case RIGHT:
                map.getPlayer().move(1, 0);
                displayMap();
                break;
            case SPACE:
                map.getPlayer().attack();
                displayMap();
                break;
            default:
                break;
        }
    }

    public void displayEQ() {
        contextEQ.setFill(Color.BLACK);
        contextEQ.fillRect(0, 0, gc.getCanvasEQ().getWidth(), gc.getCanvasEQ().getHeight());
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Cell cell = eq.getCell(x, y);
                if (inventory.getItems().size() > 0) {
                    for (int i = 0; i < inventory.getItems().size(); i++) {
                        if (cell.getTileName().equals(inventory.getItems().get(i).getTileName())) {
                            Tiles.drawTile(contextEQ, "empty", x, y);
                            Tiles2.drawTile(contextEQ, cell.getTileName(), x, y);
                            break;
                        } else {
                            Tiles.drawTile(contextEQ, cell.getTileName(), x, y);
                        }
                    }
                } else {
                    Tiles.drawTile(contextEQ, cell.getTileName(), x, y);
                }
            }
        }
    }

    public void displayMap() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (int x = 0; x < player.getViewRange(); x++) {
            for (int y = 0; y < player.getViewRange(); y++) {
                Field field = map.getField(
                        map.getPlayer().getX() + x - (player.getViewRange() / 2),
                        map.getPlayer().getY() + y - (player.getViewRange() / 2)
                );
                if (field.getActor() == player) {
                    if(field.getActor().getHealth()>30){
                       context.setFill(Color.DARKGREEN);
                    context.setFont(new Font(15));
                    }else {
                        context.setFill(Color.RED);
                    }

                    Tiles3.drawTile(context, "floor", x, y);
                    Tiles2.drawTile(context, "player2", x, y - 1);
                    context.fillText(userName, x * 64, y * 64-20);

                    Tiles2.drawTile(context, field.getTileName(), x, y);
                } else if (field.getActor() != null) {
                    context.setFill(Color.DARKGREEN);
                    context.setFont(new Font(15));
                    context.fillText(field.getTileName(), x * 64, y * 64);
                    Tiles3.drawTile(context, "floor", x, y);
                    Tiles3.drawTile(context, field.getTileName(), x, y);
                } else {
                    if (field.getTileName().equals("empty")) {

                        Tiles2.drawTile(context, field.getTileName(), x, y);
                        Tiles2.drawTile(context, field.getTileName(), x, y);
                    } else if (field.getTileName().equals("wall")) {
                        Tiles3.drawTile(context, field.getTileName(), x, y);

                    } else {
                        Tiles3.drawTile(context, "floor", x, y);
                        Tiles3.drawTile(context, field.getTileName(), x, y);


                    }

                }
            }
        }
        pickUp();
        gc.getProgressHealth().setProgress(((double) player.getHealth() / 100) * 100 / maxHealth);
        gc.getProgressMana().setProgress(((double) player.getMana() / 100) * 100 / maxMana);

    }


    /**
     * Make npc try to get close to the player,
     * and attack him if adjacent to
     *
     * @param npc - npc to be moved
     */
    private void moveNPC(Actor npc) {
        int npcX = npc.getField().getX();
        int npcY = npc.getField().getY();
        int playerX = map.getPlayer().getField().getX();
        int playerY = map.getPlayer().getField().getY();
        int viewRange = npc.getViewRange();
        if (Math.abs(playerX - npcX) <= viewRange && Math.abs(playerY - npcY) <= viewRange) {
            npc.setAlert(true);
            int nextX = Integer.compare(playerX, npcX);
            int nextY = Integer.compare(playerY, npcY);
            if (playerX == npcX + nextX && playerY == npcY + nextY) {
                // placeholder for attack function
                npc.attack(player);
                return;
            }
            // try to go around obstacles
            Field originalField = npc.getField();
            npc.move(nextX, nextY);
            moveNpcRandomly(npc, originalField);
        } else {
            npc.setAlert(false);
            Field originalField = npc.getField();
            moveNpcRandomly(npc, originalField);
        }
    }

    private void moveNpcRandomly(Actor npc, Field originalField) {
        while (npc.getField().equals(originalField)) {
            npc.move(new Random().nextInt((1 + 1) + 1) - 1, new Random().nextInt((1 + 1) + 1) - 1);
        }
    }

    private void startNpcMovement() {
        Thread moveNpcs = new Thread(() -> {
            while (true) {
                for (Actor npc : map.getNpcs()) {
                    moveNPC(npc);
                }
                displayMap();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        moveNpcs.start();
    }
}
