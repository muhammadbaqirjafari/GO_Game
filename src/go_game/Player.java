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
public class Player {
    private PlayerSpecification spec;
    
    // Constructor
    public Player(PlayerSpecification spec) {
        this.spec = spec;
    }
    
    // Getters
    public PlayerSpecification getPlayerSpecification() {
        return spec;
    }
    
    // Public Methods
    public BeadLOC makeTurn(BoardSpecification spec) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter position x : ");
        int x = sc.nextInt();
        System.out.print("Enter position y : ");
        int y = sc.nextInt();
        
        return new BeadLOC(x, y);
    }
    
}
