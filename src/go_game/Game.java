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
       player1 = new Player(new PlayerSpecification(getInput("Enter First Player Name : "), new PlayerID(1)));
       player2 = new Player(new PlayerSpecification(getInput("Enter Second Player Name : "), new PlayerID(2)));
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
        gameFinished();
    }
    
    
    private void gameFinished() {
        
    }
    
    // Private Methods
    // Muhammad Soban
    private String getInput(String s) {
         Scanner sc = new Scanner(System.in);
         
         return "Muhammad";
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
    
    // Check is Game Completed
    // Kashif
    private boolean isGameFinished() {
        return false;
    }
    
    // Place Bead on Board
    // Usama Ahmad
    // Two Rules
    private boolean placeBead(BeadSpecification spec) {
        return false;
    }
}
