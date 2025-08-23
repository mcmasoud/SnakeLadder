package com.example.demo63457;


class Move {
    public final String playerName;
    public final int from;
    public final int roll;
    public final int to;
    public final boolean transported;
    public final TransportType transportType;

    public Move(String playerName, int from, int roll, int to,
                boolean transported, TransportType transportType) {
        this.playerName = playerName;
        this.from = from;
        this.roll = roll;
        this.to = to;
        this.transported = transported;
        this.transportType = transportType;
    }
}
