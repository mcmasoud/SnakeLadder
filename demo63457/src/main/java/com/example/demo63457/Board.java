package com.example.demo63457;


import java.util.HashMap;
import java.util.Map;

class Board<T extends Cell> {
    private final Map<Integer, T> cells;
    private final int size;

    public Board(Map<Integer, T> cells, int size) {
        this.cells = new HashMap<>(cells);
        this.size = size;
    }

    public int getSize() {
        return size; }
    public T getCell(int index) {
        return cells.get(index); }

    public static Board<Cell> standard100() {
        Map<Integer, Cell> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(i, new NormalCell(i));
        }
        // Ladders
        map.put(4, new TransportCell(4, 25, TransportType.LADDER));
        map.put(13, new TransportCell(13, 46, TransportType.LADDER));
        map.put(33, new TransportCell(33, 49, TransportType.LADDER));
        map.put(50, new TransportCell(50, 69, TransportType.LADDER));
        map.put(42, new TransportCell(42, 63, TransportType.LADDER));
        map.put(62, new TransportCell(62, 81, TransportType.LADDER));
        map.put(74, new TransportCell(74, 92, TransportType.LADDER));

        // Snakes
        map.put(40, new TransportCell(40, 3, TransportType.SNAKE));
        map.put(27, new TransportCell(27, 5, TransportType.SNAKE));
        map.put(43, new TransportCell(43, 18, TransportType.SNAKE));
        map.put(54, new TransportCell(54, 31, TransportType.SNAKE));
        map.put(66, new TransportCell(66, 45, TransportType.SNAKE));
        map.put(99, new TransportCell(99, 41, TransportType.SNAKE));
        map.put(95, new TransportCell(95, 77, TransportType.SNAKE));
        map.put(89, new TransportCell(89, 53, TransportType.SNAKE));

        return new Board<>(map, 100);
    }
}