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
            board.display();
            // Making Turn
            switch(turn) {
                case 0: {
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
                    if(placeBead(new BeadSpecification(player1ID, tempLOC)))
                        turn = 1; // Switch the turn
                    break;
                }
                case 1: {
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
                    
                    // If Bead is successfully placed
                    if(placeBead(new BeadSpecification(player2ID, tempLOC)))
                        turn = 0; // Switch the turn
                    break;
                }
            }
        }
        gameFinished(player1ID, player2ID);
    }
    private boolean capturedArea(int[][] dp, int x, int y, int turn) {
        // already visited node or captured or side beads
        if(x<0 || y<0 || x==board.getBoardSpecification().getWidth() || 
            y==board.getBoardSpecification().getHeight() ||
            dp[x][y] == -1 || dp[x][y] == 3 || dp[x][y] == (turn%2+1))
            return true;
        // if empty or unexpected node
        if(dp[x][y] == 0)
            return false;
        int temp = dp[x][y];
        dp[x][y] = -1;
        if(capturedArea(dp, x-1, y, turn)&&capturedArea(dp, x+1, y,turn)&&
            capturedArea(dp, x, y-1,turn)&&capturedArea(dp,x,y+1,turn))
            dp[x][y] = 3; // captured
        else
            dp[x][y] = temp;
        return dp[x][y]==3;
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
            
    
    // After the end of the game check for winning player
    private PlayerID checkWin() {
        int p1Count = 0;
        int p2Count = 0;
        int w = board.getBoardSpecification().getWidth();
        int h = board.getBoardSpecification().getHeight();
        for(int i = 0; i<w; i++)
            for(int j = 0; j<h; j++)
                if(board.getBeadSpecification(new BeadLOC(i, j)).getPlayerID().getPlayerID()==1)
                   p1Count++;
                else if(board.getBeadSpecification(new BeadLOC(i, j)).getPlayerID().getPlayerID()==2)
                    p2Count++;
        if(p1Count==p2Count)
            return new PlayerID(0);
        else if(p1Count>p2Count)
            return new PlayerID(1);
        else
            return new PlayerID(2);
    }
    private int[][] makeDPMatrix() {
        int w = board.getBoardSpecification().getWidth();
        int h = board.getBoardSpecification().getHeight();
        int[][] dp = new int[w][h];

        for(int i = 0; i<w; i++)
            for(int j = 0; j<h; j++)
                dp[i][j] = board.getBeadSpecification(new BeadLOC(i, j)).getPlayerID().getPlayerID();

        return dp;
    }
    private void fillDP(int[][] dp, int w, int h, int turn) {
        for(int i = 0; i<w; i++)
            for(int j = 0; j<h; j++)
                if(dp[i][j] == 3)
                    dp[i][j] = turn;
    }
    // Place Bead on Board
    private boolean placeBead(BeadSpecification spec) {
        if(board.getBeadSpecification(spec.getLoc()).getPlayerID().getPlayerID() != 0)
            return false;
        int pTurn = spec.getPlayerID().getPlayerID();
        int turn = pTurn;
        int x = spec.getLoc().getX();
        int y = spec.getLoc().getY();
        int w = board.getBoardSpecification().getWidth();
        int h = board.getBoardSpecification().getHeight();
        int[][] dp = makeDPMatrix();
        dp[x][y] = pTurn;
        boolean inLoop = false;
        // capturing area and placing in dp
        while((x!=0&& turn!=board.getBeadSpecification(new BeadLOC(x-1, y)).getPlayerID().getPlayerID() &&capturedArea(dp, x-1, y, (turn%2+1) )) ||
        (x!=w-1&& turn!=board.getBeadSpecification(new BeadLOC(x+1, y)).getPlayerID().getPlayerID() &&capturedArea(dp, x+1, y, (turn%2+1) )) ||
        (y!=0&& turn!=board.getBeadSpecification(new BeadLOC(x, y-1)).getPlayerID().getPlayerID() &&capturedArea(dp, x, y-1, (turn%2+1) )) ||
        (y!=h-1&& turn!=board.getBeadSpecification(new BeadLOC(x, y+1)).getPlayerID().getPlayerID() &&capturedArea(dp, x, y+1, (turn%2+1) ))) {
            System.out.println(turn);
            fillDP(dp, w, h, turn);
            turn = turn%2+1;
            inLoop = true;
        }
        if(turn != pTurn && inLoop || !inLoop && !capturedArea(dp, x, y, turn))
        {
            board.updateTable(dp);
            return true;
        }
        else {
            return false;
        }

    }
    
    
}
