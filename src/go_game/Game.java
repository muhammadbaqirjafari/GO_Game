/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go_game;

import java.util.Scanner;

/**
 *
 * @author baqir
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    
    // Constructor
    public Game() {
       // Player Names
       String player1Name = getInput("Enter First Player Name : ", "");
       String player2Name = getInput("Enter Second Player Name : ", player1Name);
       
       player1 = new Player(new PlayerSpecification(player1Name, new PlayerID(1)));
       player2 = new Player(new PlayerSpecification(player2Name, new PlayerID(2)));
       board = new Board(new BoardSpecification(10, 10));
    }
    
    // Public Methods
    public void playGame() {
        BoardSpecification spec = board.getBoardSpecification();
        PlayerID player1ID = player1.getPlayerSpecification().getPlayerID();
        PlayerID player2ID = player2.getPlayerSpecification().getPlayerID();

        boolean player1Pass = false, player2Pass = false, isGameFinished = false;
        int turn = 0; // For Making Turn
        BeadLOC tempLOC;
        
        while(!isGameFinished) {
            // Making Turn
            switch(turn) {
                case 0: {
                    while(true) {
                        tempLOC = player1.makeTurn(spec);   // Player1 makes turn

                        // Check for Pass
                        if(tempLOC.getX() == -1) {
                            player1Pass = true;
                            // Check Game Finished Condition
                            if(player2Pass){
                                isGameFinished = true;
                            }
                            break;
                        } else if(player2Pass) {
                            player2Pass = false;
                        }
                        
                        
                        // If Bead is successfully placed get out of the loop
                        if(placeBead(new BeadSpecification(player1ID, tempLOC))) {
                            break;
                        }
                    }
                    // Check if we can remove opponents beads
                    checkTurn(player1ID);
                    turn = 1; // Switch the turn
                    break;
                }
                case 1: {
                    while(true) {
                        tempLOC = player2.makeTurn(spec); // Player2 makes Turn

                        // Check for Pass
                        if(tempLOC.getX() == -1) {
                            player2Pass = true;
                            // Check Game Finished Condition
                            if(player1Pass){
                                isGameFinished = true;
                            }
                            break;
                        } else if(player1Pass) {
                            player1Pass = false;
                        }
                        
                        // If Bead is successfully placed get out of the loop
                        if(placeBead(new BeadSpecification(player2ID, tempLOC))) {
                            break;
                        }
                    }
                    // Check if we can remove opponents beads
                    checkTurn(player2ID);
                    turn = 0; // Switch the turn
                    break;
                }
            }
        }
        gameFinished(player1ID, player2ID);
    }
    
    // This method display the winner
    private void gameFinished(PlayerID player1ID, PlayerID player2ID) {
        PlayerID winnerID = checkWin();
        
        if(winnerID == player1ID) {
            System.out.println("Player 1 is the Winner!!!");
        } else if(winnerID == player2ID) {
            System.out.println("Player 2 is the Winner!!!");
        } else {
            System.out.println("Game Draw!!!");
        }
    }
    
    // Take Input A Valid Player Name
    private String getInput(String message, String name) {
        Scanner sc = new Scanner(System.in);
        String playerName = "";
        
        System.out.print(message);
        while(true) {
            playerName = sc.nextLine();
            
            if(!playerName.equals(name) && !playerName.isBlank()) {
                break;
            }
            
            System.out.println("Enter a Valid Name!");
            System.out.print(message);
        }

        return playerName;  // Returns Name of the Player
    }
    
    // After every turn check for opponent beads
    // Muhammad Adeel
    // Remove Opponent Beads If possible
    private void checkTurn(PlayerID playerId) {
        // 
    }
    
    // After the end of the game check for winning player
    // Baqir
    private PlayerID checkWin() {
        return new PlayerID(1);
    }
    
    // Place Bead on Board
    // Usama Ahmad
    // Two Rules
    private boolean placeBead(BeadSpecification spec) {
        return false;
    }
    
    
    // This method will return total nodes on given 
    // Input : logical board where
    // board[i][j] = 0 means empty location
    // board[i][j] = 1 means player1 Bead
    // board[i][j] = 2 means player2 Bead
    private int totalNodes(int x, int y, int board[][]) {
        if(board[x][y] == 0) {
            return -1; // Means location is empty
        }
        
        int beadValue = board[x][y];
        int totalNodes[] = new int[1];
        totalNodes[0] = 0;
        
        calculateNodes(beadValue, x, y, totalNodes, board);
        
        return totalNodes[0];
    }
    
    
    // A recurssive method
    private void calculateNodes(int beadValue, int x, int y, int totalNodes[], int board[][]) {
        if(board[x][y] == 0) {
            totalNodes[0] += 1;
        }
        
        int rowLength = board[0].length;
        int columnLength = board.length;
        
        // Left Check
        if(x + 1 < rowLength && board[x+1][y] == beadValue) {
            calculateNodes(beadValue, x + 1, y, totalNodes, board);
        }
        // Right Check
        if(x - 1 > 0 && board[x-1][y] == beadValue) {
            calculateNodes(beadValue, x - 1, y, totalNodes, board);
        }
        // Up Check
        if(y - 1 > 0 && board[x][y-1] == beadValue) {
            calculateNodes(beadValue, x, y - 1, totalNodes, board);
        }
        // Down Check
        if(y + 1 < columnLength && board[x][y+1] == beadValue) {
            calculateNodes(beadValue, x, y + 1, totalNodes, board);
        }
    }
}
