package com.example.tibia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        Main.main(args);
    }

}