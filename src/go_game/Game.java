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
                    checkTurn(player2ID, tempLOC);
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
                    checkTurn(player1ID, tempLOC);
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
    
    // Remove Opponent Beads If possible
    private void checkTurn(PlayerID opponentPlayerId, BeadLOC loc) {
        int logicalBoard[][] = getLogicalBoard();
        int x = loc.getX();
        int y = loc.getY();
        int width = board.getBoardSpecification().getWidth();
        int height = board.getBoardSpecification().getHeight();
        
        // Now there are only four possibilites
        // Left Check
        if(x + 1 < width && board.getBeadSpecification(new BeadLOC(x + 1, y)).getPlayerID().equals(opponentPlayerId)) {
            if(totalNodes(x + 1, y, logicalBoard) == 0) {
                removeBeads(new BeadLOC(x + 1, y));
            }
        }
        // Right Check
        if(x - 1 > 0 && board.getBeadSpecification(new BeadLOC(x - 1, y)).getPlayerID().equals(opponentPlayerId)) {
            if(totalNodes(x - 1, y, logicalBoard) == 0) {
                removeBeads(new BeadLOC(x - 1, y));
            }
        }
        // Up Check
        if(y - 1 > 0 && board.getBeadSpecification(new BeadLOC(x, y - 1)).getPlayerID().equals(opponentPlayerId)) {
            if(totalNodes(x, y - 1, logicalBoard) == 0) {
                removeBeads(new BeadLOC(x, y - 1));
            }
        }
        // Down Check
        if(y + 1 < height && board.getBeadSpecification(new BeadLOC(x, y + 1)).getPlayerID().equals(opponentPlayerId)) {
            if(totalNodes(x, y + 1, logicalBoard) == 0) {
                removeBeads(new BeadLOC(x, y + 1));
            }
        }
    }
    
    // Remove All the beads at current location
    private void removeBeads(BeadLOC loc) {
        PlayerID opponentPlayerID = board.getBeadSpecification(loc).getPlayerID();
        // Removing Beads
        recursiveRemoveBeads(opponentPlayerID, loc.getX(), loc.getY());
    }
    
    // Recurssive Remove Bead
    private void recursiveRemoveBeads(PlayerID opponentPlayerID, int x, int y) {
        if(board.getBeadSpecification(new BeadLOC(x, y)).getPlayerID().equals(opponentPlayerID)) {
            board.removeBead(new BeadLOC(x, y));
        }
        
        // Left Check
        // Right Check
        // Up Check
        // Down Check
    }
    
    
    // After the end of the game check for winning player
    private PlayerID checkWin() {
        return new PlayerID(1);
    }
    
<<<<<<< HEAD
    // Check is Game Completed
    // Kashif
    private boolean isGameFinished() {
        return false;
    }
    // checkAreaCovered by kashif
    private boolean checkCoveredArea(int[][] dp, int pTurn, int i, int j){

    }
    
=======
>>>>>>> 1f56c4bd73e477a644e75e543e12ebe102287052
    // Place Bead on Board
    private boolean placeBead(BeadSpecification spec) {
        // Check if there is already player bead
        if(!board.getBeadSpecification(spec.getLoc()).getPlayerID().equals(new PlayerID(0))) {
            return false;
        }
        // Ok now there is empty location
        // Check if it is possible to place bead or not
        int logicalBoard[][] = getLogicalBoard();
        
        // Placing the bead on logicalBoard
        int x = spec.getLoc().getX();
        int y = spec.getLoc().getY();
        if(spec.getPlayerID().equals(player1.getPlayerSpecification().getPlayerID())) {
            logicalBoard[x][y] = 1;
        } else {
            logicalBoard[x][y] = 2;
        }   
        
        // If nodes are 0 than return false
        if(totalNodes(x, y, logicalBoard) == 0) {
            return false;
        }
        
        // Now we are safe to place bead on board
        board.placeBead(spec); // :)
        return true;
    }
    
    
    // Input : logical Board
    // Return total nodes
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
    
    
    // Return a logical board where
    // board[i][j] = 0 means empty location
    // board[i][j] = 1 means player1 Bead
    // board[i][j] = 2 means player2 Bead
    private int[][] getLogicalBoard() {
        int logicalBoard[][] = new int[board.getBoardSpecification().getWidth()][board.getBoardSpecification().getHeight()];
        
        PlayerID player1ID = player1.getPlayerSpecification().getPlayerID();
        PlayerID player2ID = player2.getPlayerSpecification().getPlayerID();
        for(int i = 0; i < logicalBoard[0].length; ++i) {
            for(int j = 0; j < logicalBoard.length; ++j) {
                // Get PlayerID at current location
                PlayerID tempPlayerID = board.getBeadSpecification(new BeadLOC(i, j)).getPlayerID();
                
                if(tempPlayerID.equals(player1ID)) {
                    logicalBoard[i][j] = 1;
                } else if (tempPlayerID.equals(player2ID)) {
                    logicalBoard[i][j] = 2;
                } else {
                    logicalBoard[i][j] = 0;
                }
            }
        }
        
        return logicalBoard;
    }
}
