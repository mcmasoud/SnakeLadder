package com.example.demo63457;


class TransportCell extends Cell {
    private final int destination;
    private final TransportType type;
    public TransportCell(int index, int destination, TransportType type) {
        super(index);
        this.destination = destination;
        this.type = type;
    }
    public int getDestination() {
        return destination;}
    public TransportType getType() {
        return type;}
}