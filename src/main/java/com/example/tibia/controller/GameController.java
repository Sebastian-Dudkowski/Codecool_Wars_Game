package com.example.tibia.controller;

import com.example.tibia.EQ.Cell;
import com.example.tibia.EQ.EQLoader;
import com.example.tibia.EqTiles;
import com.example.tibia.GameTiles;
import com.example.tibia.Main;
import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Player;
import com.example.tibia.items.Item;
import com.example.tibia.items.Key;
import com.example.tibia.map.Field;
import com.example.tibia.map.FieldType;
import com.example.tibia.map.MapLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static com.example.tibia.Main.*;
import static com.example.tibia.sounds.SoundsPlayer.playSound;

import java.util.Random;


public class GameController {

    @FXML
    private HBox hbox;

    @FXML
    private Label playerName;

    public Label getPlayerName() {
        return playerName;
    }

    public void setPlayerName(Label playerName) {
        this.playerName = playerName;
    }

    @FXML
    private Button pickUpButton;
    @FXML
    private Canvas canvas;

    @FXML
    private Label amountOfHealth;

    public Label getAmountOfHealth() {
        return amountOfHealth;
    }

    public void setAmountOfHealth(Label amountOfHealth) {
        this.amountOfHealth = amountOfHealth;
    }

    public Label getAmountOfMana() {
        return amountOfMana;
    }

    public void setAmountOfMana(Label amountOfMana) {
        this.amountOfMana = amountOfMana;
    }

    @FXML
    private Label amountOfMana;


    @FXML
    private VBox playerMenu;

    @FXML
    private BorderPane borderPaneEQ;

    public BorderPane getBorderPaneEQ() {
        return borderPaneEQ;
    }

    @FXML
    private ProgressBar progressHealth;

    public ProgressBar getProgressHealth() {
        return progressHealth;
    }

    @FXML
    private ProgressBar progressMana;

    public ProgressBar getProgressMana() {
        return progressMana;
    }

    @FXML
    private Canvas canvasEQ;

    public Canvas getCanvasEQ() {
        return canvasEQ;
    }

    public Button getPickUpButton() {
        return pickUpButton;
    }

    @FXML
    private BorderPane borderpane;

    public Canvas getCanvas() {
        return canvas;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }

    public static GraphicsContext context;
    public static GraphicsContext contextEQ;
    private static String userName;

    @FXML
    public void initialize() {
        userName = HelloController.getUserName();
        context = canvas.getGraphicsContext2D();
        contextEQ = canvasEQ.getGraphicsContext2D();
        player = new Player(HelloController.getUserName(), null, 100, 100, 35);
        eq = EQLoader.loadEQ(player);
        inventory = player.getInventory();
        getPlayerName().setText(HelloController.getUserName());
        map = MapLoader.loadMap(player);
        getBorderpane().setCenter(getCanvas());
        getBorderPaneEQ().setCenter(getCanvasEQ());
        maxHealth = player.getHealth();
        maxMana = player.getMana();
        getAmountOfHealth().setText("HP : " + player.getHealth() + "/" + maxHealth);
        getAmountOfMana().setText("Mana : " + player.getMana() + "/" + maxMana);
        displayEQ();
        getPickUpButton().addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            inventory.addItem(map.getPlayer().getField().getItem());
            displayEQ();
        });
        startMapDisplay();
        startNpcMovement();
//        playSound(opening, (float) 0.2);
    }


    public void setupKeys() {
        Main.scene.setOnKeyPressed(this::onKeyPressed);
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
                map.getPlayer().attack();
                break;
            case K:
                if (player.getInventory().getItems() != null){
                    for (Item item : player.getInventory().getItems()){
                        if (item instanceof Key){
                            ((Key) item).useKey(map.getPlayer());
                            break;
                        }
                    }
                }
                break;
            case E:
                player.pickUpItem(player.getField());
                displayEQ();
            default:
        }
    }

    public void displayEQ() {
        contextEQ.setFill(Color.BLACK);
        contextEQ.fillRect(0, 0, canvasEQ.getWidth(), canvasEQ.getHeight());
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
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
//                    context.fillText(HPline(field.getActor().getHealth()), x * 64 + 20, y * 64);
                    GameTiles.drawTile(context, FieldType.FLOOR.getTileName(), x, y);
                    GameTiles.drawTile(context, field.getTileName(), x, y);
                } else {
                    if (field.getType().equals(FieldType.EMPTY)){
                        GameTiles.drawTile(context, map.generateRandomEmptyField(x, y).getTileName(), x, y);
                    } else if (field.getItem() == null) {
                        GameTiles.drawTile(context, field.getTileName(), x, y);
                    } else {
                        GameTiles.drawTile(context, FieldType.FLOOR.getTileName(), x, y);
                        GameTiles.drawTile(context, field.getTileName(), x, y);
                    }
                }
                displayAttackAnimation(x, y);
            }
        }

        getProgressHealth().setProgress(((double) player.getHealth() / 100) * 100 / maxHealth);
        getProgressMana().setProgress(((double) player.getMana() / 100) * 100 / maxMana);
        Platform.runLater(() -> {
            getAmountOfHealth().setText("HP : " + player.getHealth() + "/" + maxHealth);
            getAmountOfMana().setText("Mana : " + player.getMana() + "/" + maxMana);
        });


    }

    /**
     * checks if player is currently attacking
     * ensures that sword flash will appear
     * on tiles that are already loaded
     * and won't be covered by another tile loading
     *
     * @param x first loop iterator in displayMap()
     * @param y second loop iterator in displayMap()
     */
    private void displayAttackAnimation(int x, int y) {
        if (player.isAttacking()
                && x == player.getViewRange() / 2 + 1
                && y == player.getViewRange() / 2 + 1
        ) {
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
                npc.attack(player);
                return;
            }
            // try to go around obstacles randomly
            Field originalField = npc.getField();
            npc.move(nextX, nextY);
            moveNpcRandomly(npc, originalField);
            playSound(npc.getWalkingSound(), (float) 0.4);
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

