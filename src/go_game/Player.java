/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go_game;

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
        double x = Math.random() * spec.getWidth() ; // [0 .. width)
        double y = Math.random() * spec.getHeight(); // [0 .. height)
       
        return new BeadLOC((int)x, (int)y);
    }
    
}
