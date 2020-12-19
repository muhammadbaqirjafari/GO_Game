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
public class PlayerSpecification {
    private String name;
    private PlayerID playerID;
    
    // Constructor
    public PlayerSpecification(String name, PlayerID playerID) {
        this.name = name;
        this.playerID = playerID;
    }
    
    // Getters
    public String getPlayerName() {
        return name;
    }
    
    public PlayerID getPlayerID() {
        return playerID;
    }
}
