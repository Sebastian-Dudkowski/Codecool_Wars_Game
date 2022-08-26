package com.example.tibia;

import com.example.tibia.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.tibia.Tiles;
import com.example.tibia.actors.Inventory;
import com.example.tibia.actors.Actor;
import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;
import com.example.tibia.map.GameMap;
import com.example.tibia.map.MapLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

     @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        playerName.setText(HelloController.getUserName());
//        displayEmptyHolders();
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        primaryStage.setScene(scene);
        displayMap();
        startNpcMovement();
    }

    private Scene scene;
    private Inventory inventory = new Inventory();


    List<GridPane> holders = new ArrayList<>();
    private Player player = new Player(HelloController.getUserName(), null, 100, 10);
    private GameMap map = MapLoader.loadMap(player);


    public void pick(ActionEvent actionEvent) {
        inventory.addItem(map.getPlayer().getField().getItem());
//        swordImageView.setOpacity(1);
        map.getPlayer().getField().setItem(null);
    }

    public void pickUp() {
        if (map.getPlayer().getField().getItem() != null) {
            pickUpButton.setDisable(false);


        } else {
            pickUpButton.setDisable(true);
        }
    }

    public void displayMap(){
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Field field = map.getField(x, y);
                Tiles.drawTile(context, field.getTileName(), x, y);
            }
        }
    }

    private ImageView convertToFxImage(BufferedImage image, int fieldSize) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        ImageView imageView = new ImageView(wr);
        imageView.setFitHeight(fieldSize);
        imageView.setFitWidth(fieldSize);

        return imageView;

    }

    private void moveNPC(Actor npc){
        int npcX = npc.getField().getX();
        int npcY = npc.getField().getY();
        int playerX = map.getPlayer().getField().getX();
        int playerY = map.getPlayer().getField().getY();
        // TODO: get rid of magic number (4)
        if ( Math.abs(playerX - npcX) <= 4 && Math.abs(playerY - npcY) <= 4 ){
            int nextX = Integer.compare(playerX, npcX);
            int nextY = Integer.compare(playerY, npcY);
            npc.move(nextX, nextY);
        }
    }

    private void startNpcMovement(){
        Thread moveNpcs = new Thread(() -> {

            while (true){
                for (Actor npc : map.getNpcs()){
                    moveNPC(npc);
                }
                try {
                    Platform.runLater(() -> {
                        displayMap();
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        });
        moveNpcs.start();
    }
}
