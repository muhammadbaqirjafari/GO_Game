/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go_game;
import java.util.*;

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
        int turn = 0; // For Making Turn
        BoardSpecification spec = board.getBoardSpecification();
        PlayerID player1ID = player1.getPlayerSpecification().getPlayerID();
        PlayerID player2ID = player2.getPlayerSpecification().getPlayerID();

        
        while(true) {
            // Making Turn
            switch(turn) {
                case 0: {
                    while(!placeBead( new BeadSpecification (player1ID, player1.makeTurn(spec)))){}
                    checkTurn(player1ID);
                    turn = 1; 
                    break;
                }
                case 1: {
                    while(!placeBead( new BeadSpecification (player2ID, player2.makeTurn(spec)))){}
                    checkTurn(player2ID);
                    turn = 0;
                    break;
                }
            }
            
            
            if(isGameFinished()) {
                PlayerID winnerID = checkWin();
                
                // Player1 is Winner
                if(winnerID == player1ID) {
                    
                } 
                // Player2 is Winner
                else if (winnerID == player2ID) {

                }
                // Draw
                else {
                    
                }
            }
        }
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
