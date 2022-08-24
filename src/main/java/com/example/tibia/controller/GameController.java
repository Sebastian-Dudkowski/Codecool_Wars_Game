package com.example.tibia.controller;

import com.example.tibia.Tiles;
import com.example.tibia.actors.Player;
import com.example.tibia.map.Field;
import com.example.tibia.map.GameMap;
import com.example.tibia.map.MapLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameController {
    private Player player = new Player(HelloController.getUserName(), null);
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
    private Label playerName;



    public void initialize() throws IOException{
        System.out.println(HelloController.getUserName());
        playerName.setText(HelloController.getUserName());
//        gpBoard.add(new ImageView("C:\\Users\\PC\\Desktop\\motors-v5.png"), 7,7);
        BufferedImage ddd = test();
        test2(ddd);
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Field field = map.getField(x, y);
                if (field.getActor() != null) {
                    context = Tiles.drawTile(context, field.getActor(), x, y);
                } else {
                    context = Tiles.drawTile(context, field, x, y);
                }
            }
        }
//        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(600, 800);
        Canvas canvas = new Canvas();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image original = new Image(getClass().getResourceAsStream("/images/tiles.png"));

        root.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(newValue.getWidth());
            canvas.setHeight(newValue.getHeight());
            gc.drawImage(original, 0, 0);
        });

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public BufferedImage test() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/images/tiles.png"));;
        return bufferedImage.getSubimage(0, 0, 16, 16);
    }

    public void test2(BufferedImage image) throws IOException {
        File pathFile = new File("testimage01.png");
        ImageIO.write(image,"png", pathFile);
    }


}
