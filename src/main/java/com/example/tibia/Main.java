package com.example.tibia;


import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Player;
import com.example.tibia.controller.HelloController;
import com.example.tibia.map.MapLoader;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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


public class Main extends Application {
@FXML
    private TextField textFieldName;

    public static String getUserName() {
        return userName;
    }
    public static String userName;
    private Player player = new Player(HelloController.getUserName(), null, 100, 10);
    private GameMap map = MapLoader.loadMap(player);
    private final int SCREEN_SIZE = 9;

    Canvas canvas = new Canvas(
            SCREEN_SIZE * Tiles.TILE_WIDTH,
            SCREEN_SIZE * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    GameController gc;
    Button pickUpItemBtn = new Button("Pick up");

    public static void main(String[] args) {
        launch(args);
    }

    public void printMenu() {
        try {
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
    public void hideButton() {
        pickUpItemBtn.setVisible(false);
    }

    public void showButton() {
        pickUpItemBtn.setVisible(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        printMenu();
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 650);
        gc = fxmlLoader.getController();
        context = gc.getCanvas().getGraphicsContext2D();
        gc.getBorderpane().setCenter(gc.getCanvas());
        primaryStage.setScene(scene);
        displayMap();
        startNpcMovement();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        primaryStage.setScene(scene);

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

            default:
                break;
        }
    }


    public void displayMap(){
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (int x = 0; x < player.getViewRange(); x++) {
            for (int y = 0; y < player.getViewRange(); y++) {
                Field field = map.getField(
                        map.getPlayer().getX() + x - (player.getViewRange() / 2),
                        map.getPlayer().getY() + y - (player.getViewRange() / 2)
                );
                Tiles.drawTile(context, field.getTileName(), x, y);
            }
        }
    }

    /**
     * Make npc try to get close to the player,
     * and attack him if adjacent to
     * @param npc - npc to be moved
     */
     private void moveNPC(Actor npc){
        int npcX = npc.getField().getX();
        int npcY = npc.getField().getY();
        int playerX = map.getPlayer().getField().getX();
        int playerY = map.getPlayer().getField().getY();
        int viewRange = npc.getViewRange();
        if ( Math.abs(playerX - npcX) <= viewRange && Math.abs(playerY - npcY) <= viewRange ){
            Field originalField = npc.getField();
            int nextX = Integer.compare(playerX, npcX);
            int nextY = Integer.compare(playerY, npcY);
            if (playerX == npcX + nextX && playerY == npcY + nextY) {
                // placeholder for attack function
                System.out.println("ATTACK!");
                return;
            }
            npc.move(nextX, nextY);
            // try to go around obstacles
            while (npc.getField().equals(originalField)){
                npc.move(new Random().nextInt((1 + 1) + 1) -1, new Random().nextInt((1 + 1) + 1) -1);
            }
        }
    }

    private void startNpcMovement(){
        Thread moveNpcs = new Thread(() -> {
            while (true){
                for (Actor npc : map.getNpcs()){
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
