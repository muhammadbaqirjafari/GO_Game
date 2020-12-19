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
public class BoardSpecification {
    private final int width;
    private final int height;
    
    // Constructor
    public BoardSpecification(int width, int height) {
        this.width = width;
        this.height = height;
    }
    // Getters
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
