package com.example.tibia.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import com.example.tibia.Tiles;
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
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

    private Scene scene;
    private Player player = new Player(HelloController.getUserName(), null, 100, 10);
    private GameMap map = MapLoader.loadMap(player);
    private Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);

    private GraphicsContext context = canvas.getGraphicsContext2D();

    @FXML
    private ImageView field00;

    @FXML
    private GridPane gpBoard,
            shieldField,
            shoesField,
            swordField,
            armorField,
            magicBeanField,
            potionField,
            potionField2,
            potionField3;

    @FXML
    private Label playerName;

    @FXML
    private Button up;

    @FXML
    private Button down;
    @FXML
    private Button left;
    @FXML
    private Button right;

    @FXML
    void upKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.UP){
            map.getPlayer().move(-1, 0);
        displayMap(map);
        }

    }
    @FXML
    void downKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.DOWN){
            map.getPlayer().move(1, 0);
        displayMap(map);
        }

    }
    @FXML
    void leftKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.LEFT){
            map.getPlayer().move(0, -1);
        displayMap(map);
        }

    }
    @FXML
    void rightKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.RIGHT){
            map.getPlayer().move(0, 1);
        displayMap(map);
        }

    }

    /**
     * INITIALIZE
     * @throws IOException
     */
    public void initialize() throws IOException {
        scene = up.getScene();
        playerName.setText(HelloController.getUserName());
        displayEmptyHolders();
        displayMap(map);
        startNpcMovement();
        refreshMap();
    }

    private void refreshMap(){
        Thread refresh = new Thread(() -> {

            while (true){
                try {
                    Thread.sleep(100);
                    Platform.runLater(() -> {
                        try {
                            displayMap(map);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        });
        refresh.start();
    }
    public BufferedImage getImage(String tileName) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/images/tiles.png"));
        switch (tileName) {
            case "player":
                return bufferedImage.getSubimage(24 * 17, 6 * 17, 16, 16);
            case "sith":
                return bufferedImage.getSubimage(30 * 17, 3 * 17, 16, 16);
            case "skeleton":
                return bufferedImage.getSubimage(29 * 17, 6 * 17, 16, 16);
            case "droid":
                return bufferedImage.getSubimage(26 * 17, 3 * 17, 16, 16);
            case "wall":
                return bufferedImage.getSubimage(10 * 17, 17 * 17, 16, 16);
            case "floor":
                return bufferedImage.getSubimage(2 * 17, 0, 16, 16);
            case "empty":
                return bufferedImage.getSubimage(0, 0, 16, 16);
            case "sword":
                return bufferedImage.getSubimage(2 * 15, 0, 16, 16);
        }
        return null;

    }

    public void displayMap(GameMap gameMap) throws IOException {
        int fieldSize = 64;
        Field center = gameMap.getPlayer().getField();
        for (int i = 0; i < player.getViewRange(); i++) {
            for (int j = 0; j < player.getViewRange(); j++) {
                int x = i + center.getX() - player.getViewRange() / 2;
                int y = j + center.getY() - player.getViewRange() / 2;
                Field field = gameMap.getField(x, y);
                BufferedImage image = getImage(field.getTileName());
                ImageView imageView = convertToFxImage(image, fieldSize);
                gpBoard.add(imageView, j, i);
            }
        }
    }

    //    List<GridPane> holders = new ArrayList<>(Arrays.asList(swordField, shoesField, armorField,
//            shieldField, potionField, potionField2, potionField3, magicBeanField));
    List<GridPane> holders = new ArrayList<>();

    public void addElementsToHolders() {

    }

    public void displayEmptyHolders() throws IOException {
        holders = new ArrayList<>(Arrays.asList(swordField, shoesField, armorField,
                shieldField, potionField, potionField2, potionField3, magicBeanField));
        int fieldSize = 48;
        for (int i = 0; i < holders.size(); i++) {
            BufferedImage image = getImage("empty");
            ImageView imageView = convertToFxImage(image, fieldSize);
            holders.get(i).add(imageView, 0, 0);

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
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        });
        moveNpcs.start();
    }

}
