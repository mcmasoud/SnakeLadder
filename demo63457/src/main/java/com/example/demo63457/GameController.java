package com.example.demo63457;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class GameController {
    private final BorderPane root = new BorderPane();
    private final Board<Cell> board = Board.standard100();
    private final BoardView boardView = new BoardView();
    private final TextArea log = new TextArea();
    private GameEngine engine;
    private final Map<Player, Color> colors = new LinkedHashMap<>();
    private Label current;

    public GameController() {
        List<Player> players = Arrays.asList(new Player("player 1"), new Player("player 2"));
        engine = new GameEngine(board, players, new SixSidedDice());
        colors.put(players.get(0), Color.RED);
        colors.put(players.get(1), Color.BLUE);

        buildUI();
        initTokens();
    }

    private void buildUI() {
        root.setPadding(new Insets(10));
        root.setCenter(boardView);

        VBox side = new VBox(10);
        side.setPadding(new Insets(10));
        side.setPrefWidth(250);

        current = new Label("Current: " + engine.getCurrentPlayer().getName());

        Button rollBtn = new Button("Roll Dice");
        rollBtn.setMaxWidth(Double.MAX_VALUE);
        rollBtn.setOnAction(e -> {
            if (engine.isFinished()) return;
            rollBtn.setDisable(true);
            Move m = engine.rollAndMove();
            Player p = findPlayerByName(m.playerName);

            int landing = m.from + m.roll;

            boardView.animateMove(p, m.from, landing, () -> {
                if (m.transported) {
                    boardView.placeToken(p, m.to);
                }
                afterMove(p, m, rollBtn);
            });
        });


        log.setEditable(false);

        side.getChildren().addAll(current, rollBtn, new Label("Log:"), log);
        root.setRight(side);
    }

    private Player findPlayerByName(String name) {
    for (Player p : engine.getPlayers()) {
        if (p.getName().equals(name)) {
            return p;
        }
    }
    throw new RuntimeException("Player not found: " + name);
}


    private void initTokens() {
        for (Player p : engine.getPlayers()) {
            boardView.ensureToken(p, colors.get(p), 1);
        }
    }

    public BorderPane getRoot() {
        return root; }

    private void afterMove(Player p, Move m, Button rollBtn) {
        String msg = p.getName() + " rolled " + m.roll + ": " + m.from + " → " + (m.from + m.roll);
        if (m.transported) {
            msg += " → " + m.to
                    + (m.transportType == TransportType.LADDER ? " (ladder up!)" : " (snake down!)");
        } else {
            msg += "";
        }



        log.appendText(msg + "\n");

        if (engine.isFinished()) {
            log.appendText("Winner: " + engine.getWinner().getName() + " 🎉\n");
        } else {
            current.setText("Current: " + engine.getCurrentPlayer().getName());
        }
        rollBtn.setDisable(false);
    }

}


