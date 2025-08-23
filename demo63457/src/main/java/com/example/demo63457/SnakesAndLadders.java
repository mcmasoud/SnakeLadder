package com.example.demo63457;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;


public class SnakesAndLadders extends Application {
    @Override
    public void start(Stage stage) {
        GameController controller = new GameController();
        Scene scene = new Scene(controller.getRoot(), 900, 700);
        stage.setTitle("Snakes & Ladders - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}

