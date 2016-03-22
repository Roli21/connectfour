package fhku.connectfour;

import android.util.Log;

public class Game {

    public static final int RED = 1;
    public static final int YELLOW = 2;
    public static final int EMPTY = 0;

    private int[][] gameState = new int[7][6];
    private int gameCounter = 0;

    public Game() {

    }

    public int getCurrentPlayer(){
        return this.gameCounter % 2 == 0 ? RED : YELLOW;
    }

    public int checkGameState() {
        //check vertical lines
        int sum4win;
        for(int i = 0; i < gameState.length; i++){                                                  // 7x alle Spalten
            for(int player = 1; player <= 2; player++){                                             // 2x für 2 Spieler
                for(int fourInALine = 0; fourInALine <= 2; fourInALine++){                          // 3x für die Möglichkeiten 0-3 / 1-4 / 2-5
                    sum4win = 0;
                    for(int j = (0 + fourInALine); j < (4 + fourInALine); j++){
                        //Log.i("CF: VL ", "[" + i + "][" + j + "]");
                        if(gameState[i][j] == player){
                            ++sum4win;
                            if(sum4win == 4){
                                Log.i("CF:", "player: " + player + " wins!");
                                return player;
                            }
                        }
                    }
                }
            }
        }
        //check horizontal lines
        for(int i = 0; i < gameState[0].length; i++){                                               // 6x alle Zeilen
            for(int player = 1; player <= 2; player++){                                             // 2x für 2 Spieler
                for(int fourInALine = 0; fourInALine <= 3; fourInALine++){                          // 4x für die Möglichkeiten 0-3 / 1-4 / 2-5 / 3-6
                    sum4win = 0;
                    for(int j = (0 + fourInALine); j < (4 + fourInALine); j++){
                        //Log.i("CF: HL ", "[" + i + "][" + j + "]");
                        if(gameState[j][i] == player){
                            ++sum4win;
                            if(sum4win == 4){
                                Log.i("CF:", "player: " + player + " wins!");
                                return player;
                            }
                        }
                    }
                }
            }
        }

        //check diagonal lines left - right - down
        for(int player = 1; player <= 2; player++){                                                 // 2x für Spieler
            for(int col = 0; col <= 3; col++){                                                      // 4x Matrix [0,1,2,3], [1,2,3,4], [2,3,4,5], [3,4,5,6]
                for(int row = 3; row <= 5; row++){                                                  // 3x Matrix [3,2,1,0], [4,3,2,1], [5,4,3,2]
                    if(gameState[col][row] == player &&                                             // Alle möglichen Verbindungen zwischen den Beiden
                            gameState[col + 1][row - 1] == player &&
                            gameState[col + 2][row - 2] == player &&
                            gameState[col + 3][row - 3] == player){
                        Log.i("CF:", "player wins: " + player);
                        return player;
                    }
                }
            }
        }

        //check diagonal lines left - right - up
        for(int player = 1; player <= 2; player++){                                                 // 2x für Spieler
            for(int col = 0; col <= 3; col++){                                                      // 4x Matrix [0,1,2,3], [1,2,3,4], [2,3,4,5], [3,4,5,6]
                for(int row = 0; row <= 2; row++){                                                  // 3x Matrix [0,1,2,3], [1,2,3,4], [2,3,4,5]
                    if(gameState[col][row] == player &&                                             // Alle möglichen Verbindungen zwischen den Beiden
                            gameState[col + 1][row + 1] == player &&
                            gameState[col + 2][row + 2] == player &&
                            gameState[col + 3][row + 3] == player){
                        Log.i("CF:", "player wins: " + player);
                        return player;
                    }
                }
            }
        }

        return 0;
    }

    public boolean checkEmpty(){
        for(int i = 0; i < gameState.length; i++){
            for(int j = 0; j < gameState[0].length; j++){
                if(gameState[i][j] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean move(int column){
        for (int i = 0; i < gameState[column].length; i++){
            if(gameState[column][i] == EMPTY){
                gameState[column][i] = getCurrentPlayer();
                gameCounter++;
                return true;
            }
        }
        return false;
    }

    public int computerMove(){
        while (true){
            int randomCol = (int)(Math.random() * (gameState.length));
            if(this.move(randomCol)){
                return randomCol;
            }
        }
    }
}
