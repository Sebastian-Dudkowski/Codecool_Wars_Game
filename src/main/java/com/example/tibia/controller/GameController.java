package com.example.tibia.controller;

import com.example.tibia.Tiles;
import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;
import com.example.tibia.map.GameMap;
import com.example.tibia.map.MapLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {
    private Scene scene;
    private Player player = new Player(HelloController.getUserName(), null, 100, 10);
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
    private GridPane shieldField;

    @FXML
    private GridPane shoesField;

    @FXML
    private GridPane swordField;
    @FXML
    private GridPane armorField;
    @FXML
    private GridPane magicBeanField;

    @FXML
    private Label playerName;

    @FXML
    private GridPane potionField;

    @FXML
    private GridPane potionField2;

    @FXML
    private GridPane potionField3;
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
        if (event.getCode() == KeyCode.UP) {
            map.getPlayer().move(-1, 0);
            displayMap(map);
        }

    }

    @FXML
    void downKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.DOWN) {
            map.getPlayer().move(1, 0);
            displayMap(map);
        }

    }

    @FXML
    void leftKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.LEFT) {
            map.getPlayer().move(0, -1);
            displayMap(map);
        }

    }

    @FXML
    void rightKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.RIGHT) {
            map.getPlayer().move(0, 1);
            displayMap(map);
        }

    }

    public void initialize() throws IOException {
        scene = up.getScene();
        System.out.println(HelloController.getUserName());
        playerName.setText(HelloController.getUserName());
        displayEmptyHolders();
        displayMap(map);

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
            case "door":
                return bufferedImage.getSubimage(8 * 17, 11*17, 16, 16);
            case "bench":
                return bufferedImage.getSubimage(8 * 17, 5*17, 16, 16);
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


}
