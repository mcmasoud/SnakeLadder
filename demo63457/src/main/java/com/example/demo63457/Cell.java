package com.example.demo63457;

public abstract class Cell {
    private final int index;
    protected Cell(int index) {
        this.index = index; }

    public int getIndex() {
        return index;
    }
}