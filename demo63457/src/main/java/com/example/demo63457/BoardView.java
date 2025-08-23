package com.example.demo63457;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

class BoardView extends StackPane {
    private final GridPane grid = new GridPane();
    private final Map<Integer, StackPane> tiles = new HashMap<>();
    private final Map<Object, Circle> tokens = new HashMap<>();

    public BoardView() {


        getChildren().add(grid);
        grid.setGridLinesVisible(false);
        for (int r = 0; r < 10; r++){
            grid.getRowConstraints().add(new RowConstraints(60));}
        for (int c = 0; c < 10; c++){
            grid.getColumnConstraints().add(new ColumnConstraints(60));}


        buildTiles();

        BackgroundImage bgImage = new BackgroundImage(
                new Image("snakes-ladders-board.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(
                        Side.LEFT, 0, true,
                        Side.TOP, 0, true
                ),
                new BackgroundSize(600, 600, false, false, false, false)
        );

        this.setBackground(new Background(bgImage));
    }

    private void buildTiles() {
        for (int i = 1; i <= 100; i++) {
            int row = 9 - (i - 1) / 10;
            int col = ((i - 1) / 10 % 2 == 0) ? (i - 1) % 10 : 9 - (i - 1) % 10;

            StackPane cell = new StackPane();
            cell.setPrefSize(60, 60);
            grid.add(cell, col, row);
            tiles.put(i, cell);
        }
    }

    public void ensureToken(Object key, Color color, int start) {
        Circle c = new Circle(12, color);
        c.setStroke(Color.BLACK);
        tokens.put(key, c);
        placeToken(key, start);
    }

    public void placeToken(Object key, int index) {
        Circle t = tokens.get(key);
        if (t == null) return;
        if (t.getParent() != null) ((Pane) t.getParent()).getChildren().remove(t);
        tiles.get(index).getChildren().add(t);
        StackPane.setAlignment(t, Pos.CENTER);
    }

    public void animateMove(Object key, int from, int to, Runnable onFinished) {
        if (from == to) {
            onFinished.run();
            return;
        }

        Timeline timeline = new Timeline();
        int step = 1;

        for (int i = from + step, j = 1; i <= to; i += step, j++) {
            int target = i;
            KeyFrame frame = new KeyFrame(
                    Duration.seconds(j * 0.3),
                    e -> placeToken(key, target)
            );
            timeline.getKeyFrames().add(frame);
        }

        timeline.setOnFinished(e -> onFinished.run());
        timeline.play();
    }}
