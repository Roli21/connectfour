package fhku.connectfour;

import java.util.Random;

public class Game {

    public static final int RED = 1;
    public static final int YELLOW = -1;
    public static final int EMPTY = 0;

    private int[][] gameState = new int[7][6];
    private int gameCounter = 0;

    public Game() {
    }

    public int getCurrentPlayer() {
        return this.gameCounter % 2 == 0 ? RED : YELLOW;
    }

    public boolean move(int column) {
        for (int i = 0; i < gameState[column].length; i++) {
            if (gameState[column][i] == EMPTY) {
                gameState[column][i] = getCurrentPlayer();
                gameCounter++;
                return true;
            }
        }
        return false;
    }

    public int computerMove() {
        while (true) {
            int randomCol = (int)(Math.random() * (gameState.length));
            if (this.move(randomCol)) {
                return randomCol;
            }
        }
    }

}
