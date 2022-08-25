package com.example.tibia.controller;

import com.example.tibia.Tiles;
import com.example.tibia.actors.Inventory;
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
    private Inventory inventory = new Inventory();


    List<GridPane> holders = new ArrayList<>();
    private Player player = new Player(HelloController.getUserName(), null, 100, 10);
    GameMap map = MapLoader.loadMap(player);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();


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
    private GridPane helmetField;
    @FXML
    private GridPane cardField;

    @FXML
    private Label playerName;

    @FXML
    private GridPane healthPotionField;

    @FXML
    private GridPane manaPotionField;

    @FXML
    private Button pickUpButton;
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
            pickUp();
        }

    }

    @FXML
    void downKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.DOWN) {
            map.getPlayer().move(1, 0);
            displayMap(map);
            System.out.println("getItem" + map.getItem());
            System.out.println("getPlayer get Field" + map.getPlayer().getField());
            System.out.println("get player" + map.getPlayer());
            pickUp();
        }
    }

    @FXML
    void leftKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.LEFT) {
            map.getPlayer().move(0, -1);
            displayMap(map);
            pickUp();
        }

    }

    @FXML
    void rightKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.RIGHT) {
            map.getPlayer().move(0, 1);
            displayMap(map);
            pickUp();

        }
    }

    public void initialize() throws IOException {

        scene = up.getScene();
        playerName.setText(HelloController.getUserName());
        displayEmptyHolders();
        displayMap(map);
    }

    @FXML
    void pick(ActionEvent event) {
        inventory.addItem(map.getPlayer().getField().getItem());
        swordImageView.setOpacity(1);
        map.getPlayer().getField().setItem(null);
    }

    void pickUp() {
        if (map.getPlayer().getField().getItem() != null) {
            pickUpButton.setDisable(false);


        } else {
            pickUpButton.setDisable(true);
        }
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
                return bufferedImage.getSubimage(3 * 17, 24 * 17, 16, 16);
            case "shoes":
                return bufferedImage.getSubimage(8 * 17, 22 * 17, 16, 16);
            case "shield":
                return bufferedImage.getSubimage(7 * 17, 26 * 17, 16, 16);
            case "armor":
                return bufferedImage.getSubimage(3 * 17, 23 * 17, 16, 16);
            case "helmet":
                return bufferedImage.getSubimage(2 * 17, 22 * 17, 16, 16);
            case "manaPotion":
                return bufferedImage.getSubimage(16 * 17, 25 * 17, 16, 16);
            case "healthPotion":
                return bufferedImage.getSubimage(17 * 17, 25 * 17, 16, 16);
            case "card":
                return bufferedImage.getSubimage(22 * 17, 4 * 17, 16, 16);
            case "door":
                return bufferedImage.getSubimage(8 * 17, 11 * 17, 16, 16);
            case "bench":
                return bufferedImage.getSubimage(8 * 17, 5 * 17, 16, 16);
        }


        return null;

    }


    public void displayMap(GameMap gameMap) throws IOException {
        int fieldSize = 64;
        Field center = gameMap.getPlayer().getField();
        System.out.println(center.toString());
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
    ImageView swordImageView;
    public void displayEmptyHolders() throws IOException {
        int fieldSize = 48;
        BufferedImage swordImage = getImage("sword");
        swordImageView = convertToFxImage(swordImage, fieldSize);
        swordImageView.setOpacity(0.2);
        swordField.add(swordImageView, 0, 0);
        BufferedImage shoeImage = getImage("shoes");
        ImageView shoesImageView = convertToFxImage(shoeImage, fieldSize);
        shoesImageView.setOpacity(0.2);
        shoesField.add(shoesImageView, 0, 0);
        BufferedImage shieldImage = getImage("shield");
        ImageView shieldImageView = convertToFxImage(shieldImage, fieldSize);
        shieldImageView.setOpacity(0.2);
        shieldField.add(shieldImageView, 0, 0);
        BufferedImage armorImage = getImage("armor");
        ImageView armorImageView = convertToFxImage(armorImage, fieldSize);
        armorImageView.setOpacity(0.2);
        armorField.add(armorImageView, 0, 0);
        BufferedImage helmetImage = getImage("helmet");
        ImageView helmetImageView = convertToFxImage(helmetImage, fieldSize);
        helmetImageView.setOpacity(0.2);
        helmetField.add(helmetImageView, 0, 0);
        BufferedImage healthPotionImage = getImage("healthPotion");
        ImageView healthPotionImageView = convertToFxImage(healthPotionImage, fieldSize);
        healthPotionImageView.setOpacity(0.2);
        healthPotionField.add(healthPotionImageView, 0, 0);
        BufferedImage manaPotionImage = getImage("manaPotion");
        ImageView manaPotionImageView = convertToFxImage(manaPotionImage, fieldSize);
        manaPotionImageView.setOpacity(0.2);
        manaPotionField.add(manaPotionImageView, 0, 0);
        BufferedImage cardImage = getImage("card");
        ImageView cardImageView = convertToFxImage(cardImage, fieldSize);
        cardImageView.setOpacity(0.2);
        cardField.add(cardImageView, 0, 0);
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
