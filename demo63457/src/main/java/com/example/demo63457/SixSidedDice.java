package com.example.demo63457;


import java.util.Random;

class SixSidedDice implements Dice {
    private final Random random = new Random();
    public int roll() {
        return 1 + random.nextInt(6); }
}