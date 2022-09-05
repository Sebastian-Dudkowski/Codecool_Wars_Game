package com.example.tibia;


import com.example.tibia.EQ.Cell;
import com.example.tibia.EQ.EQLoader;
import com.example.tibia.EQ.EQMap;
import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Inventory;
import com.example.tibia.actors.Player;
import com.example.tibia.controller.HelloController;
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

import java.io.IOException;
import java.util.Random;

import static com.example.tibia.music.MusicPlayer.*;

public class Main extends Application {
    private int maxHealth;
    private int allHealth;
    private int maxMana;
    private Inventory inventory = new Inventory();
    private Player player = new Player(HelloController.getUserName(), null, 100, 100, 35);
    private GameMap map = MapLoader.loadMap(player);
    private EQMap eq = EQLoader.loadEQ(player);
    private final int SCREEN_SIZE = 9;
    private String userName;
    private String amountHealth;
    private String amountMana;
    Canvas canvas = new Canvas(
            SCREEN_SIZE * EqTiles.TILE_WIDTH,
            SCREEN_SIZE * EqTiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    GraphicsContext contextEQ = canvas.getGraphicsContext2D();
    Canvas canvasEQ = new Canvas(
            SCREEN_SIZE * EqTiles.TILE_WIDTH,
            SCREEN_SIZE * EqTiles.TILE_WIDTH);
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
        gc.getAmountOfHealth().setText("HP : " + player.getHealth() + "/" + maxHealth);
        gc.getAmountOfMana().setText("Mana : " + player.getMana() + "/" + maxMana);
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
        startMapDisplay();
        startNpcMovement();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        playSound(opening, (float) 0.2);

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
                break;
            case S:
            case DOWN:
                map.getPlayer().move(0, 1);
                break;
            case A:
            case LEFT:
                map.getPlayer().move(-1, 0);
                break;
            case D:
            case RIGHT:
                map.getPlayer().move(1, 0);
                break;
            case SPACE:
                map.getPlayer().attack(context);
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
                            EqTiles.drawTile(contextEQ, "empty", x, y);
                            GameTiles.drawTile(contextEQ, cell.getTileName(), x, y);
                            break;
                        } else {
                            EqTiles.drawTile(contextEQ, cell.getTileName(), x, y);
                        }
                    }
                } else {
                    EqTiles.drawTile(contextEQ, cell.getTileName(), x, y);
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
                    changeColorName(field);
                    GameTiles.drawTile(context, FieldType.FLOOR.getTileName(), x, y);
                    GameTiles.drawTile(context, field.getTileName() + " 2", x, y - 1);
                    context.fillText(userName, x * 64, y * 64 - 35);
                    context.fillText(HPline(field.getActor().getHealth()), x * 64 + 20, y * 64 - 20);
                    GameTiles.drawTile(context, field.getTileName(), x, y);
                } else if (field.getActor() != null) {
                    changeColorName(field);
                    context.fillText(field.getTileName(), x * 64, y * 64 - 15);
                    context.fillText(HPline(field.getActor().getHealth()), x * 64 + 20, y * 64);
                    GameTiles.drawTile(context, FieldType.FLOOR.getTileName(), x, y);
                    GameTiles.drawTile(context, field.getTileName(), x, y);
                } else {
                    if (field.getItem() == null) {
                        GameTiles.drawTile(context, field.getTileName(), x, y);
                    } else {
                        GameTiles.drawTile(context, FieldType.FLOOR.getTileName(), x, y);
                        GameTiles.drawTile(context, field.getTileName(), x, y);
                    }
                }
                 displayAttackAnimation(x, y);
            }
        }

        pickUp(); // <----- to be moved somewhere else

        gc.getProgressHealth().setProgress(((double) player.getHealth() / 100) * 100 / maxHealth);
        gc.getProgressMana().setProgress(((double) player.getMana() / 100) * 100 / maxMana);
        Platform.runLater(() -> {
            gc.getAmountOfHealth().setText("HP : " + player.getHealth() + "/" + maxHealth);
            gc.getAmountOfMana().setText("Mana : " + player.getMana() + "/" + maxMana);
        });


    }

    /**
     * checks if player is currently attacking
     * ensures that sword flash will appear
     * on tiles that are already loaded
     * and won't be covered by another tile loading
     * @param x first loop iterator in displayMap()
     * @param y second loop iterator in displayMap()
     */
    private void displayAttackAnimation(int x, int y){
        if (player.isAttacking()
                && x == player.getViewRange() / 2 + 1
                && y == player.getViewRange() / 2 + 1
        ){
            String imageName = (player.isFacingRight()) ? "sword flash right" : "sword flash left";
            GameTiles.drawTile(context, imageName, x - 2, y - 2);
        }
    }

    private void changeColorName(Field field) {
        if (field.getActor().getHealth() > 30) {
            context.setFill(Color.BLACK);
            context.setFont(new Font("Segoe UI Black", 15));
        } else {
            context.setFill(Color.RED);
        }
    }

    private String HPline(int health) {

        String line = "-";
        if (health * 100 / maxHealth > 75) {

            line = "----";
        } else if (health * 100 / maxHealth > 50) {
            line = "---";
        } else if (health * 100 / maxHealth > 25) {
            line = "--";
        } else {
            line = "-";
        }
        return line;
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
                try {
                    Thread.sleep(500); // NPC movement speed
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        moveNpcs.start();
    }
    private void startMapDisplay() {
        Thread refreshMap = new Thread(() -> {
            while (true) {
                displayMap();
                try {
                    Thread.sleep(33); // refresh rate
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        refreshMap.start();
    }
}
