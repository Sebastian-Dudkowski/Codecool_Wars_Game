package com.example.tibia;


import com.example.tibia.actors.Player;
import com.example.tibia.controller.HelloController;
import com.example.tibia.map.MapLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class Main extends Application {

    private Player player = new Player(HelloController.getUserName(), null, 100, 10);
    private GameMap map = MapLoader.loadMap(player);
    private final int SCREEN_SIZE = 20;

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
        hideButton();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        gc = fxmlLoader.getController();
        context = gc.getCanvas().getGraphicsContext2D();
        gc.getBorderpane().setCenter(gc.getCanvas());
        primaryStage.setScene(scene);
        displayMap();
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
        Field center = map.getPlayer().getField();
        for (int i = 0; i < player.getViewRange(); i++) {
            for (int j = 0; j < player.getViewRange(); j++) {
                int x = i + center.getX() - player.getViewRange() / 2;
                int y = j + center.getY() - player.getViewRange() / 2;
                Field field = map.getField(x, y);
                Tiles.drawTile(context, field.getTileName(), x, y);
                System.out.println(field.getTileName());
            }
        }
    }
}