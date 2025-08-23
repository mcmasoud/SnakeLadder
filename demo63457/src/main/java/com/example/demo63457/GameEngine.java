package com.example.demo63457;


import java.util.*;

class GameEngine {
    private final Board<Cell> board;
    private final List<Player> players;
    private final Map<Player, Token> tokens = new HashMap<>();
    private final Dice dice;
    private int currentIndex = 0;
    private Player winner = null;

    public GameEngine(Board<Cell> board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = new ArrayList<>(players);
        this.dice = dice;
        for (Player p : players)
            tokens.put(p, new Token());
    }

    public Player getCurrentPlayer() {
        return players.get(currentIndex); }


    public Player getWinner() {
        return winner; }


    public boolean isFinished() {
        return winner != null; }


    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }


    private void nextPlayer() {

        currentIndex = (currentIndex + 1) % players.size();}


    public Move rollAndMove() {
        if (isFinished())
            return null;

        Player player = getCurrentPlayer();
        Token token = tokens.get(player);
        int from = token.getPosition();
        int roll = dice.roll();
        int to = from + roll;

        boolean transported = false;
        TransportType type = null;

        if (to >= board.getSize()) {
            to = board.getSize();
            winner = player;
        }

        else {
            Cell c = board.getCell(to);
            if (c instanceof TransportCell) {
                TransportCell tCell = (TransportCell) c;
                to = tCell.getDestination();
                transported = true;
                type = tCell.getType();
            }
        }

        token.setPosition(to);

        Move m = new Move(player.getName(), from, roll, to, transported, type);

        if (!isFinished()) nextPlayer();
        return m;
    }




}
